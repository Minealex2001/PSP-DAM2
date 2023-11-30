package ejercicio3;

/**
 * Clase que simula una puerta de entrada a un almacén
 */
public class Entrada {
    //Atributo que indica si la puerta está ocupada
    boolean ocupada;

    /**
     * Constructor
     */
    Entrada() {
        this.ocupada = false;

    }

    /**
     * Método que indica si la puerta está ocupada
     *
     * @return true si la puerta está ocupada, false en caso contrario
     */
    public boolean estaOcupada() {
        return this.ocupada;
    }

    /**
     * Método que libera la puerta
     */
    public synchronized void liberarPuerta() {
        this.ocupada = false;
    }

    /**
     * Método que intenta entrar por la puerta
     *
     * @return true si se pudo entrar, false en caso contrario
     */
    public synchronized boolean intentarEntrar() {
        if (this.ocupada){ return false;}
        this.ocupada = true;
        return true;
    }
}