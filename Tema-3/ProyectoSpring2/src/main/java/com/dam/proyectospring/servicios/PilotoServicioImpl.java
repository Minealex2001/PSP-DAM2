package com.dam.proyectospring.servicios;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.repositorios.PilotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Anotación para indicar que esta clase es un servicio
public class PilotoServicioImpl implements PilotoServicio {
    @Autowired // Anotación para inyectar la dependencia del repositorio de pilotos
    private PilotoRepositorio pilotoRepositorio;

    @Override
    public List<Piloto> findAllPilotos() {
        // Retorna todos los pilotos utilizando el repositorio de pilotos
        return pilotoRepositorio.findAll();
    }

    @Override
    public Piloto save(Piloto piloto) {
        // Guarda un piloto en la base de datos utilizando el repositorio de pilotos
        return pilotoRepositorio.save(piloto);
    }

    @Override
    public Piloto update(String id, Piloto piloto) {
        // Busca un piloto por su id utilizando el repositorio de pilotos
        Optional<Piloto> optionalPiloto = pilotoRepositorio.findById(id);
        if (optionalPiloto.isPresent()) {
            // Si el piloto existe, actualiza sus datos y lo guarda en la base de datos
            Piloto existingPiloto = optionalPiloto.get();
            existingPiloto.setNombre(piloto.getNombre());
            existingPiloto.setAbreviatura(piloto.getAbreviatura());
            existingPiloto.setNumero(piloto.getNumero());
            existingPiloto.setEquipo(piloto.getEquipo());
            existingPiloto.setPais(piloto.getPais());
            existingPiloto.setFechaNacimiento(piloto.getFechaNacimiento());
            return pilotoRepositorio.save(existingPiloto);
        } else {
            // Si el piloto no existe, retorna null
            return null;
        }
    }

    @Override
    public void delete(String id) {
        // Elimina un piloto por su id utilizando el repositorio de pilotos
        pilotoRepositorio.deleteById(id);
    }

    @Override
    public Piloto findById(String id) {
        // Busca un piloto por su id utilizando el repositorio de pilotos
        Optional<Piloto> optionalPiloto = pilotoRepositorio.findById(id);
        // Si el piloto existe, lo retorna, de lo contrario retorna null
        return optionalPiloto.orElse(null);
    }
}