package ejercicio1;

import static java.lang.Thread.*;

/**
 * Clase que representa un productor
 */
public class Productor implements Runnable {
    // Contenedor compartido
    Contenedor contenedor;

    /**
     * Constructor de la clase
     *
     * @param contenedor
     */
    public Productor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    /**
     * Método que se ejecuta al lanzar el hilo
     */
    public void run() {
        // Bucle infinito
        for (int i = 0; i < 30; i++){
            // Genera un número aleatorio
            int num = i;
            // Mientras no se pueda encolar el número, espera
            while (!contenedor.encolar(num)) {
                try {
                    sleep((int) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        contenedor.terminate();
    }
}