package com.TiendaDisco.ManejoStock.controller;

import com.TiendaDisco.ManejoStock.model.infoStock;
import com.TiendaDisco.ManejoStock.service.IInfoStockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class InfoStockController {

    @Autowired
    private IInfoStockService stockService;

    @GetMapping
    public ResponseEntity<List<infoStock>> getAllInfoStock() {
        return ResponseEntity.ok(stockService.getAllInfoStock());
    }

    @PostMapping
    public ResponseEntity<infoStock> postInfoStock(@Valid @RequestBody infoStock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.postInfoStock(stock));
    }

    @GetMapping("/{id}")
    public ResponseEntity<infoStock> getInfoID(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getInfoID(id));
    }

    @GetMapping("/producto/{nombreProducto}")
    public ResponseEntity<infoStock> getProductoInfo(@PathVariable String nombreProducto) {
        return ResponseEntity.ok(stockService.getProductoInfo(nombreProducto));
    }

    @GetMapping("/sede/{nombreSede}")
    public ResponseEntity<List<infoStock>> getSedeInfo(@PathVariable String nombreSede) {
        return ResponseEntity.ok(stockService.getSedeInfo(nombreSede));
    }

    @PutMapping("/{id}/nombre")
    public ResponseEntity<String> putNombreProducto(@PathVariable Long id, @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(stockService.putNombreProducto(id, nuevoNombre));
    }

    @PutMapping("/{id}/cantidad")
    public ResponseEntity<String> putStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        return ResponseEntity.ok(stockService.putStock(id, nuevoStock));
    }

    @PutMapping("/{id}/sede")
    public ResponseEntity<String> putSede(@PathVariable Long id, @RequestParam String nombreSede) {
        return ResponseEntity.ok(stockService.putSede(id, nombreSede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInfo(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.deleteInfo(id));
    }
}