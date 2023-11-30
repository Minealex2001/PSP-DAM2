package ejercicio2;

/**
 * Clase que representa a los palillos
 */
public class Cubierto {
    // Array de booleanos que indica si un palillo esta libre o no
    boolean[] cubiertoLibre;

    /**
     * Constructor de la clase
     *
     * @param numCubiertos
     */
    public Cubierto(int numCubiertos) {
        cubiertoLibre = new boolean[numCubiertos];
        // Se inicializa el array
        for (int i = 0; i < numCubiertos; i++) {
            // Todos los palillos estan libres
            cubiertoLibre[i] = true;
        }
    }

    /**
     * Metodo que intenta coger los palillos
     *
     * @param pos1
     * @param pos2
     * @return
     */
    public synchronized boolean intentarCogerPalillos(int pos1, int pos2) {
        boolean seConsigue = false;
        if ((cubiertoLibre[pos1]) && (cubiertoLibre[pos2])) {
            cubiertoLibre[pos1] = false;
            cubiertoLibre[pos2] = false;
            seConsigue = true;
        } //Fin del if
        return seConsigue;
    }

    // Metodo que libera los palillos
    public void liberarPalillos(int pos1, int pos2) {
        // Se liberan los palillos
        cubiertoLibre[pos1] = true;
        cubiertoLibre[pos2] = true;
    }
}