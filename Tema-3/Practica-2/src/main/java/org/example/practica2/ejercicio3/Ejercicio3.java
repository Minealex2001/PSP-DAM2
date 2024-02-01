package org.example.practica2.ejercicio3;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {

        List<String> urls = List.of("https://www.google.com", "https://www.youtube.com", "https://www.marca.com", "https://www.elperiodicomediterraneo.com", "https://www.elmundo.com", "https://www.github.com", "https://www.whatsapp.com", "https://www.linkedin.com", "https://www.reddit.com", "https://www.pinterest.com");

        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture[] futures = urls.stream()
                .map(url -> client.sendAsync(HttpRequest.newBuilder().uri(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> {
                            try {
                                File file = new File("src/main/resources/" + URI.create(url).getHost() + ".html");
                                FileWriter writer = new FileWriter(file);
                                writer.write(response.body());
                                writer.close();
                                return file;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }))
                .toArray(CompletableFuture[]::new);

//        CompletableFuture.allOf(futures).join();

        File[] files = new File[futures.length];
        for (int i = 0; i < futures.length; i++) {
            files[i] = (File) futures[i].join();
        }

        FileOutputStream fos = new FileOutputStream("src/main/resources/websites.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }

        zipOut.close();
        fos.close();
    }
}