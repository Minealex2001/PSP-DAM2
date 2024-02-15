package ejercicio3;

/**
 * Clase que simula un almacén con un número de productos
 */
public class GrandesAlmacenes {
    /**
     * Método principal
     *
     * @param args Argumentos de la línea de comandos
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //Número de clientes y productos
        final int NUMERO_CLIENTES = 200;
        final int NUMERO_PRODUCTOS = 20;

        //Creamos los objetos compartidos
        Cliente[] cliente = new Cliente[NUMERO_CLIENTES];
        Almacen almacen = new Almacen(NUMERO_PRODUCTOS);
        Entrada entrada = new Entrada();

        //Creamos los hilos
        Thread[] hilosAsociados = new Thread[NUMERO_CLIENTES];

        //Creamos los clientes y los hilos asociados
        for (int i = 0; i < NUMERO_CLIENTES; i++) {
            //Creamos el cliente
            String nombreHilo = "Cliente " + i;
            cliente[i] = new Cliente(entrada, almacen, nombreHilo);
            hilosAsociados[i] = new Thread(cliente[i]);
            hilosAsociados[i].start();
        }

        //Esperamos a que terminen todos los hilos
        for (int i = 0; i < NUMERO_CLIENTES; i++) {
            hilosAsociados[i].join();
        }
    }
}