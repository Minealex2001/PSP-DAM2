package org.alejandro;

import org.eclipse.paho.client.mqttv3.*;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class PoliceStation implements Runnable {

    private final MqttClient client;
    private final Jedis jedis;

    public static final String VEHICLES = "CHARLES:VEHICLES";
    public static final String FINEDVEHICLES = "CHARLES:FINEDVEHICLES";

    public PoliceStation(String mqttUrl, String redisUrl) {
        this.client = createMqttClient(mqttUrl);
        this.jedis = new Jedis(redisUrl, 6379);
        jedis.del(FINEDVEHICLES, VEHICLES);
    }

    private MqttClient createMqttClient(String mqttUrl) {
        MqttClient tempClient = null;
        try {
            tempClient = new MqttClient(mqttUrl, UUID.randomUUID().toString());
            tempClient.connect(createMqttConnectOptions());
            tempClient.setCallback(createMqttCallback());
            tempClient.subscribe("car/excess");
        } catch (MqttException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return tempClient;
    }

    private MqttConnectOptions createMqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        return options;
    }

    private MqttCallback createMqttCallback() {
        return new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {}
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                String[] data = mqttMessage.toString().split(":");
                if(data[0].equals("EXCESS") && data[2]!=null) {
                    int percentage = (10000/Integer.parseInt(data[1]))-100;
                    int fine;
                    if (percentage >= 10 && percentage < 20) {
                        fine = 100;
                    }else if (percentage >= 20 && percentage < 30) {
                        fine = 200;
                    }else {
                        fine = 300;
                    }
                    publishMessage("TICKET:%s:%d", data[2], fine);
                    jedis.rpush(FINEDVEHICLES, data[2]);
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        };
    }

    @Override
    public void run() {
        while (true) {
            try {
                long vehiclesLength = jedis.llen(VEHICLES);
                long finedVehiclesLength = jedis.llen(FINEDVEHICLES);
                double percentage = (double)finedVehiclesLength/vehiclesLength*100;
                System.out.printf("Total vehicles: %d\n", vehiclesLength);
                System.out.printf("Total tickets: %.2f%%(%d fined vehicles)\n", finedVehiclesLength == 0 ? 0 : percentage, finedVehiclesLength);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void publishMessage(String format, String data, int fine) throws MqttException {
        String msg = String.format(format, data, fine);
        MqttMessage message = new MqttMessage(msg.getBytes());
        client.publish("car/ticket", message);
    }
}