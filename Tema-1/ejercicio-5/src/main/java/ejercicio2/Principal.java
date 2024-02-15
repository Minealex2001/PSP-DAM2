package ejercicio2;

/**
 * Clase que representa a los palillos
 */
public class Principal {
    /**
     * Metodo principal
     *
     * @param args
     */
    public static void main(String[] args) {

        // Numero de procesos
        int procesosTotales=5;

        // Array de filosofos
        Filosofo[] filosofos =new Filosofo[procesosTotales];

        // Palillos
        Cubierto cubierto = new Cubierto(procesosTotales);

        // Array de hilos
        Thread[] hilos =new Thread[procesosTotales];

        // Se crean los filosofos
        for (int i=1; i<procesosTotales; i++){

            // Se crean los filosofos
            filosofos[i]=new Filosofo(cubierto, i, i-1);

            // Se crean los hilos
            hilos[i]=new Thread(filosofos[i]);

            // Se lanzan los hilos
            hilos[i].start();
        }
        // El primer filosofo coge el primer palillo y el ultimo
        filosofos[0]=new Filosofo(cubierto, 0, 4);

        // Se crea el hilo
        hilos[0]=new Thread(filosofos[0]);
        // Se lanza el hilo
        hilos[0].start();
    }
}