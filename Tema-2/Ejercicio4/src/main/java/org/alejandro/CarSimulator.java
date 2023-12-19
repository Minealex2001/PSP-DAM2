package org.alejandro;

import org.eclipse.paho.client.mqttv3.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class CarSimulator implements Runnable {

    private final MqttClient client;
    private final Random rand = new Random();

    public CarSimulator(String url) {
        this.client = createMqttClient(url);
    }

    private MqttClient createMqttClient(String url) {
        MqttClient tempClient = null;
        try {
            tempClient = new MqttClient(url, UUID.randomUUID().toString());
            tempClient.connect(createMqttConnectOptions());
            tempClient.setCallback(createMqttCallback());
            tempClient.subscribe("car/ticket");
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
                String[] fine = mqttMessage.toString().split(":");
                System.out.printf("(%s): %s - %.2f€%n",
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm")),
                        fine[1], Double.parseDouble(fine[2]));
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
                int speed = rand.nextInt(60, 141);
                String licensePlate = generateLicensePlate();

                if(client != null && client.isConnected()) {
                    publishMessage(licensePlate, speed);
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private String generateLicensePlate() {
        StringBuilder licensePlate = new StringBuilder();
        licensePlate.append(String.format("%04d", rand.nextInt(10000)));
        for (int i = 0; i < 3; i++) {
            licensePlate.append((char)rand.nextInt('A', 'Z'+1));
        }
        return licensePlate.toString();
    }

    private void publishMessage(String licensePlate, int speed) throws MqttException {
        String topic = "car/data";
        byte[] payload = (licensePlate + ":" + speed).getBytes();
        MqttMessage msg = new MqttMessage(payload);
        msg.setQos(0);
        msg.setRetained(true);
        client.publish(topic, msg);
    }
}