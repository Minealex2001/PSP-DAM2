package org.example.practica1;

import org.example.practica1.models.Jugador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Persecucción de enemigos \n" +
                "1. Comenzar juego \n" +
                "2. Salir");

        switch (br.readLine()) {
            case "1":
                play(br);
                break;
            case "2":
                br.close();
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void play(BufferedReader br) throws IOException {
        System.out.println("¿Numero de enemigos?");
        int numEnemigos = Integer.parseInt(br.readLine());
        Jugador jugador = new Jugador(numEnemigos);
        jugador.setSize(10, 10);
        jugador.setVisible(true);

        while (true) {

        }
    }
}
