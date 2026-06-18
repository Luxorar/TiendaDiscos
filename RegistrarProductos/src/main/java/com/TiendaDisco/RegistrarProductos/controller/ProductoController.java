package com.TiendaDisco.RegistrarProductos.controller;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.model.Producto;
import com.TiendaDisco.RegistrarProductos.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PostMapping
    public Producto postProducto(@Valid @RequestBody Producto producto) {
        return productoService.postProducto(producto);
    }

    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ProductoDTO getProductoID(@PathVariable Long id) {
        return productoService.getProductoID(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<ProductoDTO> getProductoNombre(@PathVariable String nombre) {
        return productoService.getProductoNombre(nombre);
    }

    @GetMapping("/marca/{marca}")
    public List<ProductoDTO> getProductoMarca(@PathVariable String marca) {
        return productoService.getProductoMarca(marca);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        return productoService.deleteProducto(id);
    }
}
