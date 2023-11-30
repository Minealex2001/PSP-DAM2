package Ejercicio1TCP;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException {
        // Creamos un socket TCP
        Socket socket = new Socket("localhost", 5000);

        // Creamos un flujo de salida para enviar datos al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Enviamos el mensaje "time"
        out.println("time");

        // Cerramos la conexión
        socket.close();
    }
}
