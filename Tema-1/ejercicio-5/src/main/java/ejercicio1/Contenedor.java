package ejercicio1;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un contenedor
 */
public class Contenedor {
    // Constantes
    private final int ELEMENTOS_MAXIMOS;
    // Lista que representa el contenedor
    List<Integer> cola;
    boolean terminate = false;

    // Constructor de la clase
    public Contenedor(int max) {
        cola = new LinkedList<>();
        this.ELEMENTOS_MAXIMOS = max;
        terminate = false;
    }

    // Métodos
    // Comprueba si la cola está vacía
    public synchronized boolean Vacia() {
        return cola.isEmpty();
    }

    // Comprueba si la cola está llena
    public synchronized boolean Llena() {
        int numElementos = cola.size();
        return numElementos == this.ELEMENTOS_MAXIMOS;
    }

    // Encola un número
    public synchronized boolean encolar(int numero) {
        // Si la cola está llena, no se puede encolar
        while (Llena()) {
            notifyAll();
            return false;
        }
        cola.add(numero);
        return true;
    }

    // Desencola un número
    public synchronized int desencolar() {
        // Si la cola está vacía, no se puede desencolar
        if (Vacia()) {
            return -1;
        }
        return cola.remove(0);
    }

    public void terminate() {
        terminate = true;
    }
}