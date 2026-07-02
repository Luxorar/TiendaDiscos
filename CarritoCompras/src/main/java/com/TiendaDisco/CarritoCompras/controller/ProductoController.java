package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @deprecated
 * Esta clase fue creada antes de la comunicación de los microservicios, eliminar en versionaes futuras
 */
@RestController
@RequestMapping("/api/v1/carrito/productos_p")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtiene todos los productos del sistema.
     *
     * @return lista de {@link Producto}
     */
    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAllProductos();
    }
}
