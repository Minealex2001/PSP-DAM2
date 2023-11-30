package org.alejandro.ejercicio4;

public class Consumidor extends Thread{
    private Buffer buffer;
    private static long tiempoDescanso = 1000;

    public Consumidor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        while(true){
            System.out.println("Consumidor: " + buffer.getValor());
            try{
                Thread.sleep(tiempoDescanso);
            }catch(InterruptedException e){
                System.err.println("Error en el consumidor: " + e.getMessage());
            }
        }
    }
}
