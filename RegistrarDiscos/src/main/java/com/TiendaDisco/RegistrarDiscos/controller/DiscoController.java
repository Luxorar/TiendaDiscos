package com.TiendaDisco.RegistrarDiscos.controller;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.service.IDiscoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/discos")
public class DiscoController {

    @Autowired
    private IDiscoService discoService;

    @PostMapping
    public Disco registrarDisco(@Valid @RequestBody Disco disco) {
        return discoService.postDisco(disco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscoDTO> obtenerDiscoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(discoService.getDiscoId(id));
    }

    @PutMapping("/{id}")
    public String actualizarDisco(@PathVariable Long id, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(id, disco);
    }

    @DeleteMapping("/{id}")
    public String eliminarDisco(@PathVariable Long id) {
        return discoService.deleteDisco(id);
    }

    @GetMapping
    public ResponseEntity<List<DiscoDTO>> obtenerTodosLosDiscos() {
        return ResponseEntity.ok(discoService.getAllDiscos());
    }
}
