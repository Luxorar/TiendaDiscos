package com.TiendaDisco.RegistrarProductos.controller;

import com.TiendaDisco.RegistrarProductos.model.Producto;
import com.TiendaDisco.RegistrarProductos.service.IProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(
        name="productos",
        description="Controlador para administrar los productos"
)
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Operation(
            summary="Registro de un producto",
            description="Permite agregar un nuevo producto"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Producto agregado"),
            @ApiResponse(responseCode="400",
                    description = "datos invalidos")
    })
    @PostMapping
    public Producto postProducto(@Valid @RequestBody Producto producto) {
        return productoService.postProducto(producto);
    }

    @Operation(
            summary="Obtener lista",
            description="Permite obtener la lista de los productos"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Datos retornados"),
            @ApiResponse(responseCode="400",
                    description = "Datos invalidos")
    })
    @GetMapping
    public List<Producto> getListaProducto() {
        return productoService.getListaProducto();
    }

    @Operation(
            summary="Obtencion por id",
            description="Permite obtener un producto en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto abtenido"),
            @ApiResponse(responseCode="400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{id}")
    public Producto getProductoID(@PathVariable Long id) {
        return productoService.getProductoID(id);
    }

    @Operation(
            summary="Obtencion por nombre",
            description="Permite obtener un producto en base a su nombre"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto obtenido"),
            @ApiResponse(responseCode="400",
                    description = "Datos invalidos")
    })
    @GetMapping("/nombre/{nombre}")
    public List<Producto> getProductoNombre(@PathVariable String nombre) {
        return productoService.getProductoNombre(nombre);
    }

    @Operation(
            summary="Obtencion por marca",
            description="Permite obtener productos en base a su marca"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Productos obtenidos"),
            @ApiResponse(responseCode="400",
                    description = "Datos invalidos")
    })
    @GetMapping("/marca/{marca}")
    public List<Producto> getProductoMarca(@PathVariable String marca) {
        return productoService.getProductoMarca(marca);
    }

    @Operation(
            summary="Borrar producto",
            description="Permite borrar un producto"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto eliminado"),
            @ApiResponse(responseCode="400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        return productoService.deleteProducto(id);
    }
}