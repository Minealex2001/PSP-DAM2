package ejercicio1;

/**
 * Clase que representa un consumidor
 */
public class Consumidor implements Runnable {
    // Contenedor compartido
    Contenedor contenedor;

    /**
     * Constructor de la clase
     *
     * @param contenedor
     */
    public Consumidor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    /**
     * Método que se ejecuta al lanzar el hilo
     */
    @Override
    public void run() {
        // Bucle infinito
        int num;
        while (true) {
            if (contenedor.terminate && contenedor.Vacia()) {
                break;
            }
            num = contenedor.desencolar();
            if (num != -1) {
                System.out.println("Consumidor recuperó el numero:" + num);
            }
        }
    }
}