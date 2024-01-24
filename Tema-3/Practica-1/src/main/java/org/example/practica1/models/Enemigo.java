package org.example.practica1.models;

import javafx.collections.ListChangeListener;
import lombok.Data;

@Data
public class Enemigo {

    public int x;
    public int y;
    public int numero;

    public Enemigo(Jugador jugador, int numero) {
        this.numero = numero;
        System.out.println("Enemigo creado numero " + numero);
        this.x = (int) (Math.random() * 30);
        this.y = (int) (Math.random() * 30);

        jugador.posicion.addListener((ListChangeListener<Jugador>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Jugador j : c.getAddedSubList()) {
                        mover(j);
                    }
                }
            }
        });
    }

public void mover(Jugador jugador) {
    if (jugador.getX() > x) {
        x += 2;
    } else if (jugador.getX() < x) {
        x -= 2;
    }

    if (jugador.getY() > y) {
        y += 2;
    } else if (jugador.getY() < y) {
        y -= 2;
    }
    comprobar();
    System.out.println(this);
}

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
    @Override
    public String toString() {
        return "Enemigo " + numero + " se mueve" +
                "x=" + x +
                ", y=" + y ;
    }
}