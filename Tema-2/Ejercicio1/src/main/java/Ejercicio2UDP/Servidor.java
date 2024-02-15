package Ejercicio2UDP;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    public static void main(String[] args) throws IOException {
        // Creamos un socket UDP
        DatagramSocket socket = new DatagramSocket(5001);

        // Creamos un pool de threads
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Bucle infinito para esperar peticiones
        while (true) {
            // Recibimos el paquete del cliente
            DatagramPacket paquete = new DatagramPacket(new byte[1024], 1024);
            socket.receive(paquete);

            // Creamos un thread para procesar el mensaje
            executorService.execute(() -> {
                // Descodificamos el mensaje
                byte[] mensajeCodificado = paquete.getData();
                String mensaje = new String(mensajeCodificado, 0, paquete.getLength());

                // Eliminamos los delimitadores
                mensaje = mensaje.substring(1, mensaje.length() - 1);

                // Escribimos el mensaje en un fichero
                try (FileWriter fileWriter = new FileWriter("mensajes.txt", true)) {
                    fileWriter.write(mensaje + "\n");
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
        }
    }
}
