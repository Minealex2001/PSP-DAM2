package ejercicio3;

import java.util.Random;

public class Cliente implements Runnable {

    //Atributos
    Entrada entrada;
    Almacen almacen;
    String nombre;
    Random generador;
    final int MAX_INTENTOS = 10;

    /**
     * Constructor
     *
     * @param p       Puerta de entrada al almacén
     * @param a       Almacén
     * @param nombreH Nombre del cliente
     */
    public Cliente(Entrada p, Almacen a, String nombre) {
        this.entrada = p;
        this.almacen = a;
        this.nombre = nombre;
        generador = new Random();
    }

    /**
     * Método que simula una espera aleatoria
     */
    public void esperar() {
        try {
            int ms_azar = generador.nextInt(100);
            Thread.sleep(ms_azar);
        } catch (InterruptedException ex) {
            System.out.println("Falló la espera");
        }
    }

    /**
     * Método que simula la entrada de un cliente al almacén
     */
    @Override
    public void run() {
        for (int i = 0; i < MAX_INTENTOS; i++) {
            if (!entrada.estaOcupada()) {
                if (entrada.intentarEntrar()) {
                    esperar();
                    entrada.liberarPuerta();
                    if (almacen.cogerProducto()) {
                        System.out.println(
                                this.nombre + ": cogí un producto!");
                    } else {
                        System.out.println(
                                this.nombre +
                                        ": ops, crucé pero no cogí nada");
                    }
                    return;
                }
            } else {
                esperar();
            }

        }
        //Se superó el máximo de reintentos y abandonamos
        System.out.println(this.nombre +
                ": lo intenté muchas veces y no pude");
    }

}