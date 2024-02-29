package org.example.ejercicio12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
                    List<CompletableFuture<Void>> downloadFutures = list.stream()
                            .map(urlAndName -> DownloaderAndZip.downloadFile(urlAndName.split(",")[0], urlAndName.split(",")[1]))
                            .toList();

                    CompletableFuture.allOf(downloadFutures.toArray(new CompletableFuture[0]))
                            .thenCompose(v -> DownloaderAndZip.compressFiles(list.stream().map(urlAndName -> urlAndName.split(",")[1]).collect(Collectors.toList()), "allFiles.zip"))
                            .join();
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
     *en el momento de que el usuario introduzca una URL vacía, se proceda a descargar todas las URLs cada una con un nombre de fichero (el nombre aleatorio único creado) y al terminar este proceso se comprimirán todos los archivos en un único archivo .ZIP. Esto lo deberás hacer usando Futuros de Java, de modo que la descarga de todos los archivos es la primera parte del futuro y cuando este proceso termina se ejecuta la
    compresión de todos ellos en un archivo .zip.*/
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
