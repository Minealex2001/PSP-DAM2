package ejercicio3;

public class Almacen {
    //Atributo que indica el número de productos
    private int numProductos;

    /**
     * Constructor
     *
     * @param nProductos Número de productos
     */
    public Almacen(int nProductos) {
        this.numProductos = nProductos;
    }

    /**
     * Método que simula la recogida de un producto
     *
     * @return true si se pudo coger, false en caso contrario
     */
    public boolean cogerProducto() {

        //Si hay productos, cogemos uno
        if (this.numProductos > 0) {
            //Simulamos la recogida del producto
            this.numProductos--;
            return true;
        }
        return false;
    }
}