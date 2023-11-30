package org.alejandro.ejercicio4;

public class Buffer {
    private boolean vacio;
    private int valor;

    Buffer(){
        vacio = true;
    }

    public boolean estaVacio(){
        return vacio;
    }

    public synchronized void anadir(int valor){
        vacio = false;
        this.valor = valor;
    }

    public synchronized int getValor(){
        vacio = true;
        return valor;
    }
}
