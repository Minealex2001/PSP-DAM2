package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.*;

public class MQTTPublicar {
    public static void main(String[] args) {
        // Crear un ID único para el publicador
        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher;
        try {
            // Crear un nuevo cliente MQTT
            publisher = new MqttClient("tcp://ec2-184-72-89-48.compute-1.amazonaws.com:1883",publisherId);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        try {
            // Conectar el cliente MQTT
            publisher.connect(options);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        // Crear un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter message: ");
        String message = scanner.nextLine();
        System.out.println("Enter topic: ");
        String topic = scanner.nextLine();
        try {
            // Crear un ExecutorService para enviar el mensaje en un hilo separado
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            Future<Boolean> result = executorService.submit(new MQTTPublisher(publisher, message, topic));
            if (result.get()) {
                System.out.printf("MQTT message sended");
            } else {
                System.out.printf("MQTT message NOT sended");
            }
            // Cerrar el ExecutorService
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // Desconectar y cerrar el cliente MQTT
            publisher.disconnect();
            publisher.close();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }


    public static class MQTTPublisher implements Callable<Boolean> {
        IMqttClient client;
        String message;
        String topic;

        public MQTTPublisher(IMqttClient client, String message, String topic) {
            // Constructor para el publicador MQTT
            this.client = client;
            this.message = message;
            this.topic = topic;
        }

        @Override
        public Boolean call() throws Exception {
            // Esperar un segundo antes de enviar el mensaje
            Thread.sleep(1000);
            if (!client.isConnected()) {
                return false;
            }
            // Crear el mensaje MQTT y publicarlo
            byte[] payload = message.getBytes();
            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(0);
            msg.setRetained(true);
            client.publish(topic, msg);
            return true;
        }
    }
}
