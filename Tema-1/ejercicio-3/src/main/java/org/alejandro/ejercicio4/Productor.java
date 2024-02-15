package org.alejandro.ejercicio4;

public class Productor extends Thread{
    private Buffer buffer;
    private static long tiempoDescanso = 1000;

    public Productor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        int i = 0;
        while(true){
            buffer.anadir(i);
            System.out.println("Productor añade: " + i);
            i++;
            try{
                Thread.sleep(tiempoDescanso);
            }catch(InterruptedException e){
                System.err.println("Error en el productor: " + e.getMessage());
            }
        }
    }
}
