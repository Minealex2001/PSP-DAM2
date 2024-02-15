package org.example;

public class Main {
    public static void main(String[] args) {

        //Creamos un objeto de la clase Utilidades
        Utilidades utilidades = new Utilidades();
        //Creamos un array de 100 numeros y booleanos
        int totalPrimos = 100;
        int[] numerosPrimos = new int[totalPrimos];
        boolean[] sonPrimos = new boolean[totalPrimos];

        //Rellenamos el array con numeros aleatorios
        utilidades.rellenarArray(numerosPrimos);

        //Iniciamos el tiempo
        long tiempo = System.nanoTime();

        //Comprobamos si son primos
        for (int i = 0; i < numerosPrimos.length; i++) {
            sonPrimos[i] = utilidades.naive(numerosPrimos[i]);
        }

        //Paramos el tiempo
        tiempo = System.nanoTime() - tiempo;

        //Mostramos los resultados
        for (int i = 0; i < numerosPrimos.length; i++) {
            System.out.println(numerosPrimos[i] + " es primo " + sonPrimos[i]);
        }

        //Mostramos el tiempo
    System.out.println("Tiempo: " + (double) (tiempo/1000000000) + " segundos");


    }
}