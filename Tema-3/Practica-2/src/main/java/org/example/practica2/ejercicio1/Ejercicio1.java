package org.example.practica2.ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class Ejercicio1 {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introduce la URL: ");

        String url = "https://" + reader.readLine();

        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<HttpRequest> request = CompletableFuture.supplyAsync(() -> HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build());

        CompletableFuture<HttpResponse<String>> response = request.thenCompose(req -> client.sendAsync(req, HttpResponse.BodyHandlers.ofString()));

        System.out.println(response.get().body());
    }
}
