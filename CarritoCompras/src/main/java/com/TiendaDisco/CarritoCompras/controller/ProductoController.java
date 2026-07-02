package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la consulta de productos del carrito.
 * <p>Expone endpoints para listar todos los productos disponibles.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/carrito/productos")
@Tag(
        name = "Productos del carrito",
        description = "Consulta de productos en el carrito"
)
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos
     */
    @Operation(
            summary = "Obtener todos los productos",
            description = "Obtiene todos los productos registrados en el carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Productos obtenidos"),
            @ApiResponse(responseCode = "500", description = "Problema del servidor")
    })
    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAllProductos();
    }
}
