package com.TiendaDisco.RegistrarSede.controller;

import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.model.Sede;
import com.TiendaDisco.RegistrarSede.service.SedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para la gestion de las sedes.
 * Se encarga de procesar las peticiones http y delegar la logica al servicio correspondiente.
 * * Ruta base: /api/v1/Sede
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/v1/Sede")
public class SedeController {
    @Autowired
    private SedeService service;

    /**
     * Endpoint para registrar una nueva sede en el sistema.
     * * @param disco Objeto con los datos de la sede a registrar
     * @return La sede persistido
     */
    @PostMapping
    public Sede postSede(@RequestBody Sede sede){
        return service.postSede(sede);
    }

    /**
     * Endpoint para obtener la lista completa de sedes registradas.
     * * @return Una respuesta HTTP 200 con la lista completa de sedes en formato DTO.
     */
    @GetMapping
    public List<SedeDTO> getAllSedes(){
        return service.getAllSedes();
    }

    /**
     * Endpoint para buscar la información detallada de las sedes específicas.
     * * @param id El identificador único de la sede enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO del disco, o 400/404 si hay un error.
     */
    @GetMapping("{id}")
    public SedeDTO getSedeId(@Valid @PathVariable Long id){
        return service.getSedeId(id);
    }

    /**
     * Endpoint para modificar los datos de una sede ya existente.
     * * @param id El identificador de la sede a modificar.
     * @param s El objeto con los nuevos datos de la sede.
     * @return Un mensaje de confirmación en formato String.
     */
    @PutMapping("{id}")
    public String PutSede(@Valid @RequestBody Sede s, @PathVariable Long id){
        return service.putSede(id, s);
    }

    /**
     * Endpoint para eliminar una sede del catálogo.
     * * @param id El identificador de la sede que se desea borrar.
     * @return Un mensaje de confirmación de la eliminación.
     */
    @DeleteMapping("{id}")
    public String deleteEnvio(@PathVariable Long id){
        return service.deleteSedeId(id);
    }
}
