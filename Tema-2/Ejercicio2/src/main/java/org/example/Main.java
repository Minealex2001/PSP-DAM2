package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        while(true) {
            // Crear un BufferedReader para leer la entrada del usuario
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Elige una opción: ");
            System.out.println("1. Ver el chat");
            System.out.println("2. Publicar un mensaje");
            System.out.println("3. Salir");
            // Convertir la entrada del usuario a un entero
            int opcion = Integer.parseInt(br.readLine());
            switch (opcion) {
                // Opción para leer el chat
                case 1:
                    System.out.println("Escribe que chat quieres leer:");
                    System.out.println("- /chat/todos/");
                    System.out.println("- /chat/julen/alejandro");
                    System.out.println("- /chat/alejandro/juelen");
                    System.out.print("Escribe aquí: ");
                    String chat = br.readLine();
                    MQTTRecibir.leerchat(chat);
                    break;
                case 2:
                    // Opción para publicar un mensaje
                    MQTTPublicar.main(args);
                    break;
                case 3:
                    // Opción para salir del programa
                    System.exit(0);
                    break;
                default:
                    // Opción por defecto si el usuario introduce un número no válido
                    System.out.println("Opción no válida");
                    break;
            }
        }

    }
}
