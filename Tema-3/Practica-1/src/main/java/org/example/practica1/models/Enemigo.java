package org.example.practica1.models;

import javafx.collections.ListChangeListener;
import lombok.Data;

// Clase Enemigo
@Data
public class Enemigo {

    // Coordenadas del enemigo
    public int x;
    public int y;

    // Número del enemigo
    public int numero;

    // Constructor del enemigo
    public Enemigo(Jugador jugador, int numero) {
        this.numero = numero;
        System.out.println("Enemigo creado numero " + numero);

        // Inicializa las coordenadas del enemigo con valores aleatorios
        this.x = (int) (Math.random() * 30);
        this.y = (int) (Math.random() * 30);

        // Añade un listener a la lista de posiciones del jugador
        jugador.posicion.addListener((ListChangeListener<Jugador>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Jugador j : c.getAddedSubList()) {
                        // Mueve el enemigo cuando el jugador se mueve
                        mover(j);
                    }
                }
            }
        });
    }

    // Método para mover el enemigo
    public void mover(Jugador jugador) {
        if (jugador.getX() > x) {
            x += 1;
        } else if (jugador.getX() < x) {
            x -= 1;
        }

        if (jugador.getY() > y) {
            y += 1;
        } else if (jugador.getY() < y) {
            y -= 1;
        }

        // Comprueba si el enemigo está dentro de los límites
        comprobar();

        // Imprime la posición actual del enemigo
        System.out.println(this);
    }

    // Método para comprobar si el enemigo está dentro de los límites
    private void comprobar() {
        if (y >= 30) {
            y = 30;
        }
        if (y <= 0) {
            y = 0;
        }
        if (x >= 30) {
            x = 30;
        }
        if (x <= 0) {
            x = 0;
        }
    }

    // Método toString para imprimir la posición del enemigo
    @Override
    public String toString() {
        return "Enemigo " + numero + " se mueve" +
                "x=" + x +
                ", y=" + y ;
    }
}