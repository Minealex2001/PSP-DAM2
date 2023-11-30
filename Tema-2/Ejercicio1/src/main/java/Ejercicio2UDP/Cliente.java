package Ejercicio2UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;

public class Cliente {

    public static void main(String[] args) throws IOException {
        // Creamos un socket UDP
        DatagramSocket socket = new DatagramSocket();

        // Leemos el texto del usuario
        System.out.print("Introduce el texto: ");
        String texto = new BufferedReader(new InputStreamReader(System.in)).readLine();

        // Codificamos el texto en base64
        byte[] textoCodificado = Base64.getEncoder().encode(texto.getBytes());

        // Creamos un paquete UDP
        DatagramPacket paquete = new DatagramPacket(textoCodificado, textoCodificado.length, InetAddress.getByName("localhost"), 5001);

        // Enviamos el paquete al servidor
        socket.send(paquete);

        // Cerramos la conexión
        socket.close();
    }
}
