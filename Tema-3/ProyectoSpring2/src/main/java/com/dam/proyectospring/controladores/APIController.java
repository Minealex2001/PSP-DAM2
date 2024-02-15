package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Anotación para indicar que esta clase es un controlador REST
@RequestMapping("/api") // Anotación para mapear las solicitudes a la ruta "/api" a esta clase
public class APIController {
    @Autowired // Anotación para inyectar la dependencia del servicio de pilotos
    private PilotoServicio pilotoServicio;

    @GetMapping("/pilotos") // Anotación para mapear las solicitudes GET a "/api/pilotos" a este método
    public ResponseEntity<List<Piloto>> getAllPilotos() {
        // Obtiene todos los pilotos utilizando el servicio de pilotos
        List<Piloto> pilotos = pilotoServicio.findAllPilotos();
        // Retorna una respuesta con los pilotos y el estado HTTP 200 (OK)
        return new ResponseEntity<>(pilotos, HttpStatus.OK);
    }

    @PostMapping("/pilotos") // Anotación para mapear las solicitudes POST a "/api/pilotos" a este método
    public ResponseEntity<Piloto> createPiloto(@RequestBody Piloto piloto) {
        // Crea un nuevo piloto utilizando el servicio de pilotos
        Piloto nuevoPiloto = pilotoServicio.save(piloto);
        // Retorna una respuesta con el piloto creado y el estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevoPiloto, HttpStatus.CREATED);
    }

    @GetMapping("/pilotos/{id}") // Anotación para mapear las solicitudes GET a "/api/pilotos/{id}" a este método
    public ResponseEntity<Piloto> getPiloto(@PathVariable String id) {
        // Obtiene un piloto por su id utilizando el servicio de pilotos
        Piloto piloto = pilotoServicio.findById(id);
        // Retorna una respuesta con el piloto y el estado HTTP 200 (OK)
        return new ResponseEntity<>(piloto, HttpStatus.OK);
    }

    @PutMapping("/pilotos/{id}") // Anotación para mapear las solicitudes PUT a "/api/pilotos/{id}" a este método
    public ResponseEntity<Piloto> updatePiloto(@PathVariable String id, @RequestBody Piloto piloto) {
        // Actualiza un piloto existente utilizando el servicio de pilotos
        Piloto pilotoActualizado = pilotoServicio.update(id, piloto);
        // Retorna una respuesta con el piloto actualizado y el estado HTTP 200 (OK)
        return new ResponseEntity<>(pilotoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/drivers/{id}") // Anotación para mapear las solicitudes DELETE a "/api/drivers/{id}" a este método
    public ResponseEntity<Void> deletePiloto(@PathVariable String id) {
        // Elimina un piloto por su id utilizando el servicio de pilotos
        pilotoServicio.delete(id);
        // Retorna una respuesta sin contenido y el estado HTTP 204 (NO CONTENT)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}