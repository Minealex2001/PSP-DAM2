package org.alejandro;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

    public static void main(String[] args) {
        String url = "34.228.162.124";
        String mqttUrl = String.format("tcp://%s:1883", url);

        CarSimulator simulator;
        try {
            simulator = new CarSimulator(mqttUrl);
        } catch (MqttException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        Radar radar;
        try {
            radar = new Radar(mqttUrl, url);
        } catch (MqttException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        PoliceStation policeStation;
        try {
            policeStation = new PoliceStation(mqttUrl, url);
        } catch (MqttException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        Thread simulatorThread = new Thread(simulator);
        Thread radarThread = new Thread(radar);
        Thread policeStationThread = new Thread(policeStation);

        simulatorThread.start();
        radarThread.start();
        policeStationThread.start();
    }
}