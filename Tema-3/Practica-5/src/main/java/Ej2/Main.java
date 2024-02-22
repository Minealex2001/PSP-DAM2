package Ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {

    public static void main(String[] args) throws IOException {
        Downloader downloader = new Downloader();
        ObservableList<String> urls = FXCollections.observableArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        urls.addListener(downloader);

        System.out.println("Si quieres terminar de ejecutar el programa escribe 'exit'");
        String url = "";

        do {
            System.out.println("Introduce la url del archivo que quieres descargar : ");
            url = br.readLine();

            if (!url.equalsIgnoreCase("exit")) {
                if (!isValidURL(url)) {
                    System.out.println("URL no válida. Por favor, introduce un URL con un protocolo válido (e.g., http://, https://)");
                } else {                        
                    try {
                        urls.add(new URI(url).toString());
                    } catch (URISyntaxException e) {
                        System.err.println("Error al crear la URI: " + e.getMessage());
                    }
                }
            }
        } while (!url.equalsIgnoreCase("exit"));
    }

    @SuppressWarnings("deprecation")
    private static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
