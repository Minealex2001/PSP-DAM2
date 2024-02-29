package org.example.ejercicio12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Random;

/**
 * Clase principal que implementa la lógica de la aplicación.
 */
public class Main {


    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        DownloaderAndZip downloaderAndZip = new DownloaderAndZip();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addListener(downloaderAndZip);
        String url;
        do {
            System.out.println("Ingrese la URL del archivo a descargar: ");
            try {
                url = br.readLine();
                if (url.equals("")){
                    System.out.println("Se va a proceder a descargar y comprimir los ficheros");
                    break;
                }
                list.add("https://" + url + "," + generateRandomString(20));
            } catch (IOException e) {
                System.err.println("Error al leer la URL");
            }
        }while (true);


        
    }

    /**
     * Genera una cadena de caracteres aleatoria de la longitud especificada.
     * @param length la longitud de la cadena de caracteres a generar
     * @return una cadena de caracteres aleatoria
     */
    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    } 
}
