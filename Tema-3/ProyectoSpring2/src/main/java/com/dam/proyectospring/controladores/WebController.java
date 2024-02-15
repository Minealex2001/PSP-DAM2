package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // Anotación para indicar que esta clase es un controlador
public class WebController {
    @Autowired // Anotación para inyectar la dependencia del servicio de pilotos
    private PilotoServicio pilotoServicio;

    @RequestMapping(value ="/") // Anotación para mapear la ruta raíz a este método
    public String index(Model model) {
        // Obtiene todos los pilotos utilizando el servicio de pilotos
        List<Piloto> pilotos = pilotoServicio.findAllPilotos();

        // Añade los pilotos al modelo para que puedan ser utilizados en la vista
        model.addAttribute("pilotos", pilotos);

        // Retorna el nombre de la vista (en este caso, "index")
        return "index";
    }
}