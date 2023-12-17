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

    public Radar(String mqttUrl, String redisUrl) throws MqttException {
        this.redisUrl = redisUrl;
        this.client = new MqttClient(mqttUrl, UUID.randomUUID().toString());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect();
        client.setCallback(new MqttCallback() {
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
        });
        client.subscribe("car/data");
    }

    @Override
    public void run() {
        try(Jedis jedis = new Jedis(redisUrl, 6379)){
            do {
                try {
                    if(licensePlate != null){
                        if (speed > 80){
                            String msg = String.format("EXCESS:%d:%s", speed, licensePlate);
                            MqttMessage message = new MqttMessage(msg.getBytes());
                            client.publish("car/excess", message);
                        }
                        jedis.rpush(VEHICLES, licensePlate);
                        licensePlate = null;
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }while (true);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}