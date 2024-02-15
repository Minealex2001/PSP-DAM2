package org.alejandro.ejercicio4;

public class Main{
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Consumidor hiloConsumidor = new Consumidor(buffer);
        Productor hiloProductor = new Productor(buffer);
        hiloConsumidor.start();
        hiloProductor.start();
    }
}
