package ejercicio1;

public class Principal {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {
        // Constantes
        final int PRODUCTORES_MAX = 3;
        final int CONSUMIDORES_MAX = 2;
        final int ELEMENTOS_MAX = 4;

        // Creación de los hilos
        Thread[] hilosProductor;
        Thread[] hilosConsumidor;

        // Inicialización de los hilos
        hilosProductor = new Thread[PRODUCTORES_MAX];
        hilosConsumidor = new Thread[CONSUMIDORES_MAX];

        // Creación del contenedor
        Contenedor contenedor = new Contenedor(ELEMENTOS_MAX);

        // Inicialización de los hilos de productores
        for (int i = 0; i < PRODUCTORES_MAX; i++) {
            Productor productor = new Productor(contenedor);
            hilosProductor[i] = new Thread(productor);
            hilosProductor[i].start();
        }

        // Inicialización de los hilos de consumidores
        for (int i = 0; i < CONSUMIDORES_MAX; i++) {
            Consumidor consumidor = new Consumidor(contenedor);
            hilosConsumidor[i] = new Thread(consumidor);
            hilosConsumidor[i].start();
        }

        try {
            // Espera a que terminen los hilos de productor
            for (int i = 0; i < PRODUCTORES_MAX; i++) {
                hilosProductor[i].join();
            }

            // Espera a que terminen los hilos de consumidor
            for (int i = 0; i < CONSUMIDORES_MAX; i++) {
                hilosConsumidor[i].join();
            }
        } catch (InterruptedException e) {
            System.err.println("Error en join");
        }
    }

}