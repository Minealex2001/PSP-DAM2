package Ej2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javafx.collections.ListChangeListener;

public class Downloader implements ListChangeListener<String> {

    Path directorioWebs = Paths.get("src/web");

    @Override
    public void onChanged(Change<? extends String> change) { 
        String url = change.getList().get(change.getList().size() - 1);

        try {
            if (Files.notExists(directorioWebs)) {
                Files.createDirectory(directorioWebs);
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String nombreFichero = "Web_" + change.getList().size() + ".html";
            Path fichero = directorioWebs.resolve(nombreFichero);

            Files.writeString(fichero, response.body(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            System.out.println("Se ha descargado el archivo " + url); 

        } catch (IOException e) {
            System.err.println("Error al crear el directorio, descargar o guardar la web: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Descarga interrumpida: " + e.getMessage());
        } 
    }
}
