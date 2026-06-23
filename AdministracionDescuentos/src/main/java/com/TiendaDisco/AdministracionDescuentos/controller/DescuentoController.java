package com.TiendaDisco.AdministracionDescuentos.controller;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
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
    public ResponseEntity<List<DescuentoDTO>> getAllDescuentos() {
        return ResponseEntity.ok(descuentoService.getAllDescuentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoDTO> getDescuentoId(@PathVariable Long id) {
        return ResponseEntity.ok(descuentoService.getDescuentoId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<DescuentoDTO> getDescuentoNombre(@RequestBody String nombre) {
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

    @PostMapping("/descuento/{nombreDescuento}")
    public ResponseEntity<String> agregarDisco(@PathVariable String nombreDescuento, @RequestBody Long idDisco) {
        return ResponseEntity.ok(descuentoService.agregarDisco(nombreDescuento, idDisco));
    }

    @DeleteMapping("/descuento/{nombreDescuento}")
    public ResponseEntity<String> quitarDisco(@PathVariable String nombreDescuento, @RequestBody Long idDisco) {
        return ResponseEntity.ok(descuentoService.quitarDisco(nombreDescuento, idDisco));
    }

    @PostMapping("/producto/{nombreDescuento}")
    public ResponseEntity<String> agregarProducto(@PathVariable String nombreDescuento, @RequestBody Long idProducto) {
        return ResponseEntity.ok(descuentoService.agregarProducto(nombreDescuento, idProducto));
    }

    @DeleteMapping("/producto/{nombreDescuento}")
    public ResponseEntity<String> quitarProducto(@PathVariable String nombreDescuento, @RequestBody Long idProducto) {
        return ResponseEntity.ok(descuentoService.quitarProducto(nombreDescuento, idProducto));
    }
}
