package org.example.practica1.models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

// Clase Jugador que extiende JFrame e implementa KeyListener
public class Jugador extends JFrame implements KeyListener{

    // Coordenadas del jugador
    public int x;
    public int y;

    // Lista observable de la posición del jugador
    public ObservableList<Jugador> posicion = FXCollections.observableArrayList();

    // Lista de enemigos
    List<Enemigo> enemigos = new ArrayList<>();

    // Constructor del jugador
    public Jugador(int numEnemigos){
        // Inicializa las coordenadas del jugador con valores aleatorios
        this.x = (int) (Math.random() * 30);
        this.y = (int) (Math.random() * 30);

        // Crea los enemigos
        for (int i = 1; i <= numEnemigos; i++){
            enemigos.add(new Enemigo(this, i));
        }

        // Configura el KeyListener
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    // Método toString para imprimir la posición del jugador
    @Override
    public String toString() {
        return "Jugador se mueve " + "x=" + x + ", y=" + y ;
    }

    // Método vacío para manejar el evento keyTyped
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Método para manejar el evento keyPressed
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                y += 1;
                break;
            case KeyEvent.VK_S:
                y -= 1;
                break;
            case KeyEvent.VK_A:
                x -= 1;
                break;
            case KeyEvent.VK_D:
                x += 1;
                break;
        }
        // Comprueba si el jugador está dentro de los límites
        comprobar();

        // Añade la posición actual a la lista de posiciones
        posicion.add(this);

        // Imprime la posición actual
        System.out.println(this);
    }

    // Método para comprobar si el jugador está dentro de los límites
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

    // Método vacío para manejar el evento keyReleased
    @Override
    public void keyReleased(KeyEvent e) {
    }
}