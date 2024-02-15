package com.dam.proyectospring.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Data // Anotación de Lombok para generar getters, setters, equals, hashCode y toString
@NoArgsConstructor // Anotación de Lombok para generar un constructor sin argumentos
@AllArgsConstructor // Anotación de Lombok para generar un constructor con todos los argumentos
@Document("pilotos") // Anotación de Spring Data MongoDB para indicar que esta clase es un documento de MongoDB
public class Piloto implements Serializable {
    @Id // Anotación de Spring Data MongoDB para indicar que este campo es el id del documento
    private String id;
    @Field("driver") // Anotación de Spring Data MongoDB para mapear este campo con el campo "driver" en el documento de MongoDB
    private String nombre;
    @Field("abbreviation") // Anotación de Spring Data MongoDB para mapear este campo con el campo "abbreviation" en el documento de MongoDB
    private String abreviatura;
    @Field("no") // Anotación de Spring Data MongoDB para mapear este campo con el campo "no" en el documento de MongoDB
    private int numero;
    @Field("team") // Anotación de Spring Data MongoDB para mapear este campo con el campo "team" en el documento de MongoDB
    private String equipo;
    @Field("country") // Anotación de Spring Data MongoDB para mapear este campo con el campo "country" en el documento de MongoDB
    private String pais;
    @Field("date_of_birth") // Anotación de Spring Data MongoDB para mapear este campo con el campo "date_of_birth" en el documento de MongoDB
    private LocalDate fechaNacimiento;

    // Constructor personalizado para crear un objeto Piloto con una fecha de nacimiento en formato String
    public Piloto(String nombre, String abreviatura, int numero, String equipo, String pais, String fechaNacimiento) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.numero = numero;
        this.equipo = equipo;
        this.pais = pais;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento); // Convierte la fecha de nacimiento de String a LocalDate
    }
}