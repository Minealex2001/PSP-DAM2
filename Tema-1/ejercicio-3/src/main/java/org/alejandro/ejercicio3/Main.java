package org.alejandro.ejercicio3;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        AtomicInteger numeroPersonalizado = new AtomicInteger(0);

        HiloPersonalizado hilo1 = new HiloPersonalizado(numeroPersonalizado);
        HiloPersonalizado hilo2 = new HiloPersonalizado(numeroPersonalizado);
        HiloPersonalizado hilo3 = new HiloPersonalizado(numeroPersonalizado);
        HiloPersonalizado hilo4 = new HiloPersonalizado(numeroPersonalizado);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El valor final es: " + numeroPersonalizado.get());
    }

}
