package org.alejandro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que representa un terminal.
 */
public class Shell {
    /**
     * Ejecuta comandos en el terminal.
     * <p>
     *     Los comandos se introducen por teclado y se ejecutan en el terminal.
     *     Los comandos se ejecutan en un proceso hijo del proceso del terminal.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        /*
         * El comando se lee por teclado.
         * <p>
         *     Se lee el comando por teclado y se ejecuta en el terminal.
         *     Si el comando es "exit" se cierra el terminal.
         *     Si el comando es "ultimo-comando" se muestra el último comando ejecutado.
         *     Si el comando es otro se ejecuta en el terminal.
         *     Si el comando contiene ">" se redirige la salida a un fichero.
         *     Si el comando no contiene ">" se muestra la salida por pantalla.
         *     Si el comando no contiene ">" y no tiene salida se muestra el fichero donde se ha redirigido la salida.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Command lastCommand = null;

        /**
         * Bucle infinito.
         */
        while (true) {
            System.out.print("Introduce el comando:");
            String input = reader.readLine();

            /**
             * Si el comando es "exit" se cierra el terminal.
             */
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Cerrando del terminal.");
                break;

                /**
                 * Si el comando es "ultimo-comando" se muestra el último comando ejecutado.
                 */
            } else if (input.equalsIgnoreCase("ultimo-comando")) {
                if (lastCommand != null) {
                    System.out.println("Último comando:");
                    System.out.println(lastCommand);
                } else {
                    System.out.println("No hay ultimo comando.");
                }
                /**
                 * Si el comando es otro se ejecuta en el terminal.
                 */
            } else {
                /**
                 * Se crea un objeto Command con el comando introducido.
                 */
                Command terminal = new Command(input);

                /**
                 * Se ejecuta el comando.
                 */
                String output = terminal.ejecutar();

                /**
                 * Si el comando no contiene ">" se muestra la salida por pantalla.
                 */
                if (output.isEmpty()) {
                    System.out.println("Salida en el archivo: " + terminal.getSalida());
                } else {
                    System.out.println("Salida del comando:");
                    System.out.println(output);
                }

                /**
                 * Se guarda el comando como último comando ejecutado.
                 */
                lastCommand = terminal;
            }
        }
    }
}