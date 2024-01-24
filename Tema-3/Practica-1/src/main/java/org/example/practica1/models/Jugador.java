package org.example.practica1.models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

public class Jugador extends JFrame implements KeyListener{

    public int x;
    public int y;
    public ObservableList<Jugador> posicion = FXCollections.observableArrayList();
    List<Enemigo> enemigos = new ArrayList<>();

    public Jugador(int numEnemigos){
        this.x = (int) (Math.random() * 30);
        this.y = (int) (Math.random() * 30);
        for (int i = 1; i <= numEnemigos; i++){
            enemigos.add(new Enemigo(this, i));
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public String toString() {
        return "Jugador se mueve " + "x=" + x + ", y=" + y ;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

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
        comprobar();
        posicion.add(this);
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
    public void keyReleased(KeyEvent e) {
    }
}