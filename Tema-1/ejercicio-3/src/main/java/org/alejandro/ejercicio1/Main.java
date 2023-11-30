package org.alejandro.ejercicio1;

public class Main {

    public static void main(String[] args) {
        NumeroPersonalizado numeroPersonalizado = new NumeroPersonalizado();
        numeroPersonalizado.setNumero(0);

        HiloPersonalizado hilo1 = new HiloPersonalizado(numeroPersonalizado);
        HiloPersonalizado hilo2 = new HiloPersonalizado(numeroPersonalizado);
        HiloPersonalizado hilo3 = new HiloPersonalizado(numeroPersonalizado);
        HiloPersonalizado hilo4 = new HiloPersonalizado(numeroPersonalizado);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        System.out.println("El valor final es: " + numeroPersonalizado.getNumero());
    }

}
