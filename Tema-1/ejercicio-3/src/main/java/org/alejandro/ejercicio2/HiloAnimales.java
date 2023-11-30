package org.alejandro.ejercicio2;

public class HiloAnimales extends Thread {
    private final String animal;
    private final double probabilidadCaerse;

    public HiloAnimales(String animal, double probabilidadCaerse, int priority){
        this.animal = animal;
        this.probabilidadCaerse = probabilidadCaerse;
        this.setPriority(priority);
    }

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++){
            if (Math.random() < probabilidadCaerse){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    System.err.println("Error en el hilo: " + e.getMessage());
                }
            }
        }
        System.out.println("El " + animal + " ha llegado a la meta");
    }

}
