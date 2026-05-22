package com.TiendaDisco.AdministracionDescuentos.controller;

import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.service.IDescuentoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/descuentos")
public class DescuentoController {

    @Autowired
    private IDescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<List<Descuento>> getAllDescuentos() {
        return ResponseEntity.ok(descuentoService.getAllDescuentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descuento> getDescuentoId(@PathVariable Long id) {
        return ResponseEntity.ok(descuentoService.getDescuentoId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Descuento> getDescuentoNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(descuentoService.getDescuentoNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Descuento> postDescuento(@Valid @RequestBody Descuento d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoService.postDescuento(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putDescuento(@PathVariable Long id, @Valid @RequestBody Descuento d) {
        return ResponseEntity.ok(descuentoService.putDescuento(id, d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDescuento(@PathVariable Long id) {
        return ResponseEntity.ok(descuentoService.deleteDescuento(id));
    }

    @PostMapping("/{nombreDescuento}/discos/{idDisco}")
    public ResponseEntity<String> agregarDisco(@PathVariable String nombreDescuento, @PathVariable Long idDisco) {
        return ResponseEntity.ok(descuentoService.agregarDisco(nombreDescuento, idDisco));
    }
}