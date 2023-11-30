package org.alejandro;

import lombok.Data;

import java.io.*;
import java.nio.file.Path;

/**
 * Clase que representa un comando.
 * <p>
 *     Un comando se compone de una serie de argumentos y un fichero donde se redirige la salida.
 *     Los argumentos se ejecutan en el terminal.
 *     La salida se redirige a un fichero.
 *     Si el comando no tiene salida y no se ha redirigido la salida se muestra la salida por pantalla.
 */
@Data
public class Command {

    /**
     * Argumentos del comando.
     */
    private String argumentos;
    private String fichero;
    private Long pid;
    private String salida = null;
    private int valorSalida = -1;
    private Process ps;

    /**
     * Constructor de la clase.
     * @param argumentos
     * @param redirectPath
     */
    public Command(String[] argumentos, String redirectPath) {
        this.argumentos = String.join(" ", argumentos);
        this.fichero = redirectPath;
    }

    /**
     * Constructor de la clase.
     * @param argumentos
     */
    public Command(String argumentos) {
        /**
         * Si el comando contiene ">" se redirige la salida a un fichero.
         */
        if (argumentos.contains(">")) {
            String[] parts = argumentos.split(">");
            this.argumentos = parts[0].trim();
            this.fichero = parts[1].trim();
            /**
             * Si el comando no contiene ">" se muestra la salida por pantalla.
             */
        } else {
            this.argumentos = argumentos;
            this.fichero = "";
        }
    }

    /**
     * Ejecuta el comando.
     * <p>
     *     El comando se ejecuta en el terminal.
     * @return salida del comando.
     */
    public String ejecutar() {
        try {
            /**
             * Se crea un proceso hijo del proceso del terminal.
             */
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", argumentos);
            pb.redirectErrorStream(true);

            /**
             * Se ejecuta el proceso.
             */
            Process process = pb.start();
            pid = process.pid();

            /**
             * Se lee la salida del proceso.
             */
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                salida = reader.lines().reduce("", (line1, line2) -> line1 + "\n" + line2);
            }

            /**
             * Se espera a que el proceso termine.
             */
            valorSalida = process.waitFor();

            /**
             * Si el comando no contiene ">" y no tiene salida se muestra el fichero donde se ha redirigido la salida.
             */
            if (!fichero.isEmpty()) {
                try (FileWriter fw = new FileWriter(fichero)) {
                    fw.write(salida);
                }
            }

            /**
             * Se devuelve la salida del proceso.
             */
            return salida;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Devuelve una cadena con la información del comando.
     * @return cadena con la información del comando.
     */
    @Override
    public String toString() {
        String result = "Comando: " + argumentos + "\n";
        result += "Numero de argumentos: " + argumentos.split(" ").length + "\n";
        result += "Argumentos: " + argumentos + "\n";
        result += "PID: " + pid + "\n";
        result += "Salida:\n" + salida + "\n";
        result += "Valor de salida: " + valorSalida + "\n";
        return result;
    }

    /**
     * Devuelve una cadena con la información del comando.
     * @return cadena con la información del comando.
     */
    public String getSalida() {
        return fichero == null ? "" : fichero;
    }
}
