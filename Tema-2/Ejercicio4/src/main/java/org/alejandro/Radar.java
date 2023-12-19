package org.alejandro;

import org.eclipse.paho.client.mqttv3.*;
import redis.clients.jedis.Jedis;
import java.util.UUID;

public class Radar implements Runnable {

    private MqttClient client;
    private int speed;
    private String licensePlate;
    private final String redisUrl;

    public static final String VEHICLES = "CHARLES:VEHICLES";

    public Radar(String mqttUrl, String redisUrl) {
        this.redisUrl = redisUrl;
        this.client = createMqttClient(mqttUrl);
    }

    private MqttClient createMqttClient(String mqttUrl) {
        MqttClient tempClient = null;
        try {
            tempClient = new MqttClient(mqttUrl, UUID.randomUUID().toString());
            tempClient.connect(createMqttConnectOptions());
            tempClient.setCallback(createMqttCallback());
            tempClient.subscribe("car/data");
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
            public void messageArrived(String topic, MqttMessage mqttMessage) {
                String[] data = mqttMessage.toString().split(":");
                licensePlate = data[0];
                speed = Integer.parseInt(data[1]);
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        };
    }

    @Override
    public void run() {
        try(Jedis jedis = new Jedis(redisUrl, 6379)){
            while (true) {
                try {
                    if(licensePlate != null){
                        if (speed > 80){
                            publishMessage("EXCESS:%d:%s", speed, licensePlate);
                        }
                        jedis.rpush(VEHICLES, licensePlate);
                        licensePlate = null;
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void publishMessage(String format, int speed, String licensePlate) throws MqttException {
        String msg = String.format(format, speed, licensePlate);
        MqttMessage message = new MqttMessage(msg.getBytes());
        client.publish("car/excess", message);
    }
}