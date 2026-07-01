package com.TiendaDisco.RegistrarDiscos.controller;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.service.ITituloService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que expone los endpoints para la gestión de las canciones (títulos).
 * Permite realizar operaciones CRUD sobre las pistas que pertenecen a los discos del catálogo.
 * * Ruta base: /api/v1/titulos
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController @RequestMapping("/api/v1/titulos")
public class TituloController {


    @Autowired
    private ITituloService tituloService;

    /**
     * Endpoint para registrar un nuevo título asociado a un disco.
     * * @param titulo Objeto con los datos de la canción a registrar (validado automáticamente).
     * @return El título persistido en la base de datos.
     */
    @PostMapping
    public Titulo registrarTitulo(@Valid @RequestBody Titulo titulo) {
        return tituloService.postTitulo(titulo);
    }

    /**
     * Endpoint para buscar la información detallada de un título específico por su ID.
     * * @param id El identificador único del título enviado por URL.
     * @return El objeto Título encontrado.
     */
    @GetMapping("/{id}")
    public Titulo obtenerTituloPorId(@PathVariable Long id) {
        return tituloService.getTituloId(id);
    }

    /**
     * Endpoint para modificar los datos de un título ya existente.
     * * @param id El identificador del título a modificar.
     * @param titulo El objeto con los nuevos datos que actualizarán a la canción.
     * @return Un mensaje de confirmación en formato String.
     */
    @PutMapping("/{id}")
    public String actualizarTitulo(@PathVariable Long id, @Valid @RequestBody Titulo titulo) {
        return tituloService.putTitulo(id, titulo);
    }

    /**
     * Endpoint para eliminar un título de la base de datos de la tienda.
     * * @param id El identificador del título que se desea borrar.
     * @return Un mensaje de confirmación de la eliminación.
     */
    @DeleteMapping("/{id}")
    public String eliminarTitulo(@PathVariable Long id) {
        return tituloService.deleteTitulo(id);
    }

    /**
     * Endpoint para obtener el listado completo de todos los títulos registrados.
     * * @return Una respuesta HTTP 200 con la lista de títulos.
     */
    @GetMapping
    public ResponseEntity<List<Titulo>> obtenerTodosLosTitulos() {
        return ResponseEntity.ok(tituloService.getAllTitulos());
    }
}
