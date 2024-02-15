package com.dam.proyectospring;

import com.dam.proyectospring.modelos.Piloto;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class ProyectoSpringApplication {
    public static void main(String[] args) throws IOException {
        // Inicia la aplicación Spring Boot
        SpringApplication.run(ProyectoSpringApplication.class, args);

        // Crea un WebClient que apunta a la API REST
        final WebClient webClient = WebClient.create("http://localhost:8080/api");

        // Crea un BufferedReader para leer la entrada del usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String id, nombre, abreviatura, equipo, pais, fechaNacimiento;
        int numero;

        // Muestra un menú al usuario
        System.out.println("Por favor, elige una opción:");
        System.out.println("1. Mostrar a todos los pilotos.");
        System.out.println("2. Mostrar un piloto dado un id.");
        System.out.println("3. Crear un piloto con nuevos datos.");
        System.out.println("4. Actualizar un piloto dado un id concreto.");
        System.out.println("5. Borrar un piloto dado un id.");

        // Realiza una acción dependiendo de la opción elegida por el usuario
        switch (Integer.parseInt(reader.readLine())) {
            case 1:
                // Realiza una solicitud GET a la API REST para obtener todos los pilotos
                webClient.get().uri("/pilotos").retrieve().bodyToFlux(String.class).subscribe(System.out::println);
                break;
            case 2:
                // Solicita al usuario que introduzca un id de piloto
                System.out.println("Introduce el id del piloto que quieres buscar:");
                id = reader.readLine();

                // Realiza una solicitud GET a la API REST para obtener el piloto con ese id
                webClient.get().uri("/pilotos/" + id).retrieve().bodyToMono(String.class).subscribe(System.out::println);
                break;
            case 3:
                // Solicita al usuario que introduzca los detalles del nuevo piloto
                System.out.println("Introduce el nombre del piloto:");
                nombre = reader.readLine();
                System.out.println("Introduce la abreviatura del piloto:");
                abreviatura = reader.readLine();
                System.out.println("Introduce el número del piloto:");
                numero = Integer.parseInt(reader.readLine());
                System.out.println("Introduce el equipo del piloto:");
                equipo = reader.readLine();
                System.out.println("Introduce el país del piloto:");
                pais = reader.readLine();
                System.out.println("Introduce la fecha de nacimiento del piloto:");
                fechaNacimiento = reader.readLine();

                // Realiza una solicitud POST a la API REST para crear un nuevo piloto con esos detalles
                webClient.post().uri("/pilotos").contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(new Piloto(nombre, abreviatura, numero, equipo, pais, fechaNacimiento))).retrieve().bodyToMono(String.class).subscribe(System.out::println);
                break;
            case 4:
                // Solicita al usuario que introduzca un id de piloto y los nuevos detalles del piloto
                System.out.println("Introduce el id del piloto que quieres actualizar:");
                id = reader.readLine();
                System.out.println("Introduce el nombre del piloto:");
                nombre = reader.readLine();
                System.out.println("Introduce la abreviatura del piloto:");
                abreviatura = reader.readLine();
                System.out.println("Introduce el número del piloto:");
                numero = Integer.parseInt(reader.readLine());
                System.out.println("Introduce el equipo del piloto:");
                equipo = reader.readLine();
                System.out.println("Introduce el país del piloto:");
                pais = reader.readLine();
                System.out.println("Introduce la fecha de nacimiento del piloto:");
                fechaNacimiento = reader.readLine();

                // Realiza una solicitud PUT a la API REST para actualizar el piloto con ese id
                webClient.put().uri("/pilotos/" + id).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(new Piloto(nombre, abreviatura, numero, equipo, pais, fechaNacimiento))).retrieve().bodyToMono(String.class).subscribe(System.out::println);
                break;

            case 5:
                // Solicita al usuario que introduzca un id de piloto
                System.out.println("Introduce el id del piloto que quieres borrar:");
                id = reader.readLine();

                // Realiza una solicitud DELETE a la API REST para eliminar el piloto con ese id
                webClient.delete().uri("/pilotos/" + id).retrieve().bodyToMono(String.class).subscribe(System.out::println);
                break;
        }
    }
}