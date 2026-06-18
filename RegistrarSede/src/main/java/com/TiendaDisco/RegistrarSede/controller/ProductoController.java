package com.TiendaDisco.RegistrarSede.controller;

import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Producto")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @PostMapping
    public Producto postProducto(@RequestBody Producto producto){
        return service.postProducto(producto);
    }

    @GetMapping
    public List<Producto> getAllProductos(){
        return service.getAllProductos();
    }

    @GetMapping("{id}")
    public Producto getProductoId(@Valid @PathVariable Long id){
        return service.getProductoId(id);
    }

    @PutMapping("{id}")
    public String putProducto(@Valid @RequestBody Producto p, @PathVariable Long id){
        return service.putProducto(id, p);
    }

    @DeleteMapping("{id}")
    public String deleteProducto(@PathVariable Long id){
        return service.deleteProducto(id);
    }
}
