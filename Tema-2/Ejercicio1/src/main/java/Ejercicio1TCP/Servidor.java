package Ejercicio1TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

    public static void main(String[] args) throws IOException {
        // Creamos un socket TCP
        ServerSocket serverSocket = new ServerSocket(5000);

        // Esperamos a que un cliente se conecte
        Socket socket = serverSocket.accept();

        // Creamos un flujo de entrada para recibir datos del cliente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Leemos el mensaje del cliente
        String mensaje = in.readLine();

        // Si el mensaje es "time", devolvemos la hora actual
        if (mensaje.equals("time")) {
            // Obtenemos la hora actual
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String hora = sdf.format(date);

            // Enviamos la hora al cliente
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(hora);
        }

        // Cerramos la conexión
        socket.close();
    }
}
