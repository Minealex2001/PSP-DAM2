package org.example.practica1;

import org.example.practica1.models.Jugador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Clase principal del juego
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Persecucción de enemigos \n" +
                "1. Comenzar juego \n" +
                "2. Salir");

        // Maneja la entrada del usuario
        switch (br.readLine()) {
            case "1":
                // Comienza el juego
                play(br);
                break;
            case "2":
                // Cierra el BufferedReader y termina el programa
                br.close();
                System.out.println("Saliendo...");
                break;
            default:
                // Maneja una entrada no válida
                System.out.println("Opción no válida");
                break;
        }
    }

    // Método para comenzar el juego
    private static void play(BufferedReader br) throws IOException {
        System.out.println("¿Numero de enemigos?");
        int numEnemigos = Integer.parseInt(br.readLine());

        // Crea un nuevo jugador con el número de enemigos especificado
        Jugador jugador = new Jugador(numEnemigos);
        jugador.setSize(10, 10);
        jugador.setVisible(true);

        // Bucle infinito para mantener el juego en ejecución
        while (true) {

        }
    }
}