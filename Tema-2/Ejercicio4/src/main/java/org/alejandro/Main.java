package org.alejandro;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

    public static void main(String[] args) {
        String url = "34.228.162.124";
        String mqttUrl = String.format("tcp://%s:1883", url);

        CarSimulator simulator = new CarSimulator(mqttUrl);
        Radar radar = new Radar(mqttUrl, url);
        PoliceStation policeStation = new PoliceStation(mqttUrl, url);

        Thread simulatorThread = new Thread(simulator);
        Thread radarThread = new Thread(radar);
        Thread policeStationThread = new Thread(policeStation);

        simulatorThread.start();
        radarThread.start();
        policeStationThread.start();
    }
}