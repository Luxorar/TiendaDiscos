package com.TiendaDisco.AdministracionVentas.controller;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import com.TiendaDisco.AdministracionVentas.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaDTO> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @PostMapping
    public ResponseEntity<VentaDTO> postVenta(@Valid @RequestBody Venta v) {
        return ResponseEntity.ok(ventaService.postVenta(v));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<VentaDTO> getVentaId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getVentaId(id));
    }

    @GetMapping("user/{u}")
    public List<VentaDTO> getVentaUser(@PathVariable String u) {
        return ventaService.getVentaUser(u);
    }

    @GetMapping("productos/{id}")
    public List<Producto> getProductoReciboId(@PathVariable Long id) {
        return ventaService.getProductoReciboId(id);
    }

    @DeleteMapping("{id}")
    public void delVenta(@PathVariable Long id) {
        ventaService.delVenta(id);
    }
}
