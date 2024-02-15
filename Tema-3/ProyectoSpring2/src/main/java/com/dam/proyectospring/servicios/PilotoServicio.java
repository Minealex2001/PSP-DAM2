package com.dam.proyectospring.servicios;

import com.dam.proyectospring.modelos.Piloto;

import java.util.List;

// Esta interfaz define los métodos que deben ser implementados por cualquier clase que proporcione servicios de pilotos
public interface PilotoServicio {
    // Método para obtener todos los pilotos
    List<Piloto> findAllPilotos();

    // Método para actualizar un piloto existente dado su id y los nuevos datos del piloto
    Piloto update(String id, Piloto piloto);

    // Método para eliminar un piloto dado su id
    void delete(String id);

    // Método para buscar un piloto dado su id
    Piloto findById(String id);

    // Método para guardar un nuevo piloto
    Piloto save(Piloto piloto);
}