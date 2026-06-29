package com.TiendaDisco.RegistrarDiscos.controller;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.service.ITituloService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/titulos")
public class TituloController {

    @Autowired
    private ITituloService tituloService;

    //==================REGISTRA UN TITULO================================
    @PostMapping
    public Titulo registrarTitulo(@Valid @RequestBody Titulo titulo) {
        return tituloService.postTitulo(titulo);
    }

    //==================OBTIENE TITULO POR ID================================
    @GetMapping("/{id}")
    public Titulo obtenerTituloPorId(@PathVariable Long id) {
        return tituloService.getTituloId(id);
    }

    //==================MODIFICA UN TITULO================================
    @PutMapping("/{id}")
    public String actualizarTitulo(@PathVariable Long id, @Valid @RequestBody Titulo titulo) {
        return tituloService.putTitulo(id, titulo);
    }

    //==================ELIMINA UN TITULO================================
    @DeleteMapping("/{id}")
    public String eliminarTitulo(@PathVariable Long id) {
        return tituloService.deleteTitulo(id);
    }

    //==================OBTIENE TODOS LOS TITULOS================================
    @GetMapping
    public ResponseEntity<List<Titulo>> obtenerTodosLosTitulos() {
        return ResponseEntity.ok(tituloService.getAllTitulos());
    }
}
