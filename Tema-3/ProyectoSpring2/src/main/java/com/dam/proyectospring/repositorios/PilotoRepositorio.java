package com.dam.proyectospring.repositorios;

import com.dam.proyectospring.modelos.Piloto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// Esta interfaz define los métodos que deben ser implementados por cualquier clase que proporcione repositorios de pilotos
public interface PilotoRepositorio extends MongoRepository<Piloto, String> {
    // Método para buscar un piloto por su nombre
    Piloto findByNombre(String nombre);

    // Método para buscar pilotos cuyo nombre contenga una cadena de texto específica
    List<Piloto> findByNombreContaining(String nombre);

    // Método para buscar pilotos de un equipo específico y ordenarlos por su número
    List<Piloto> findByEquipoOrderByNumero(String equipo);
}