package com.TiendaDisco.RegistrarDiscos.controller;

import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.service.IDiscoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/productos")
public class DiscoController {

    @Autowired
    private IDiscoService discoService;

    @PostMapping
    public Disco registrarDisco(@Valid @RequestBody Disco disco) {
        return discoService.postDisco(disco);
    }

    @GetMapping("/{id}")
    public Disco obtenerDiscoPorId(@PathVariable Long id) {
        return discoService.getDiscoId(id);
    }

    @PutMapping("/{id}")
    public String actualizarDisco(@PathVariable Long id, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(id, disco);
    }

    @DeleteMapping("/{id}")
    public String eliminarDisco(@PathVariable Long id) {
        return discoService.deleteDisco(id);
    }
}