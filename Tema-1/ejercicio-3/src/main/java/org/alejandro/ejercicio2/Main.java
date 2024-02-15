package org.alejandro.ejercicio2;

public class Main {
    public static void main(String[] args){
        HiloAnimales liebre = new HiloAnimales("liebre", 0.2, 10 );
        HiloAnimales tortuga = new HiloAnimales("tortuga", 0.05, 1);
        HiloAnimales caballo = new HiloAnimales("caballo", 0.1, 5);
        HiloAnimales perro = new HiloAnimales("perro", 0.01, 3);


        liebre.start();
        tortuga.start();
        caballo.start();
        perro.start();

        try{
            liebre.join();
            tortuga.join();
            caballo.join();
            perro.join();
        }catch(InterruptedException e){
            System.err.println("Error en el hilo: " + e.getMessage());
        }
        System.out.println("Carrera finalizada");
    }
}
