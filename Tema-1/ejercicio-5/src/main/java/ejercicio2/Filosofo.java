package ejercicio2;

import java.util.Random;

/**
 * Clase que representa a un filosofo
 */
public class Filosofo implements Runnable {

    // Palillos que tiene a su disposicion
    Cubierto cubierto;
    // Posicion de los palillos que tiene a su disposicion
    int cubiertoIzq, cubiertoDer;

    /**
     *
     * @param cubierto
     * @param cubiertoIzq
     * @param cubiertoDer
     */
    public Filosofo(Cubierto cubierto, int cubiertoIzq, int cubiertoDer) {
        this.cubierto = cubierto;
        this.cubiertoDer = cubiertoDer;
        this.cubiertoIzq = cubiertoIzq;
    }

    // Metodo que se ejecuta cuando se lanza el hilo
    public void run() {
        // Bucle infinito
        while (true) {
            boolean palillosCogidos;
            palillosCogidos = this.cubierto.intentarCogerPalillos(cubiertoIzq, cubiertoDer);
            // Si consigue coger los palillos, come y los libera
            if (palillosCogidos) {
                comer();
                this.cubierto.liberarPalillos(cubiertoIzq, cubiertoDer);
                pensar();
            }
        }
    }

    // Metodo que simula el hecho de comer
    private void comer() {
        System.out.println("Filosofo " + Thread.currentThread().getName() + " esta comiendo");
        esperarTiempoAleatorio();
    }

    // Metodo que simula el hecho de esperar un tiempo aleatorio
    private void esperarTiempoAleatorio() {
        // Se espera un tiempo aleatorio
        Random generador = new Random();
        int aleatorio = generador.nextInt(3);
        // Se pasa a milisegundos
        try {
            Thread.sleep(aleatorio);
        } catch (InterruptedException ex) {
            System.out.println("Fallo la espera");
        }
    }

    // Metodo que simula el hecho de dormir
    private void pensar() {
        System.out.println("Filosofo " + Thread.currentThread().getName() + " esta pensando");
        esperarTiempoAleatorio();
    }
}
