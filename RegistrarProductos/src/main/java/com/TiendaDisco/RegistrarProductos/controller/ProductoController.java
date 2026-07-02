package com.TiendaDisco.RegistrarProductos.controller;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
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

/**
 * Controlador REST que expone los endpoints para la gestion del catalogo de productos.
 * Se encarga de procesar las peticiones http y delegar la logica al servicio correspondiente.
 * * Ruta base: /api/v1/productos
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/productos")
@Tag(
        name="productos",
        description="Controlador para administrar los productos"
)
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    /**
     * Endpoint para registrar un nuevo producto en el sistema.
     * * @param producto Objeto con los datos del producto a registrar
     * @return El producto persistido
     */
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

    /**
     * Endpoint para obtener el catálogo completo de productos registrados.
     * * @return Una respuesta HTTP 200 con la lista completa de discos en formato DTO.
     */
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
    public List<ProductoDTO> getAllProductos() {
        return productoService.getAllProductos();
    }

    /**
     * Endpoint para buscar la información detallada de un producto específico.
     * * @param id El identificador único del producto enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO del producto, o 400/404 si hay un error.
     */
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
    public ProductoDTO getProductoID(@PathVariable Long id) {
        return productoService.getProductoID(id);
    }

    /**
     * Endpoint para buscar la información detallada de un producto específico.
     * * @param nombre El nombre del producto enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO del disco, o 400/404 si hay un error.
     */
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
    public List<ProductoDTO> getProductoNombre(@PathVariable String nombre) {
        return productoService.getProductoNombre(nombre);
    }

    /**
     * Endpoint para buscar la información detallada de varios productos específicos.
     * * @param marca La marca del producto enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO del producto, o 400/404 si hay un error.
     */
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
    public List<ProductoDTO> getProductoMarca(@PathVariable String marca) {
        return productoService.getProductoMarca(marca);
    }

    /**
     * Endpoint para eliminar un producto del catálogo.
     * * @param id El identificador del producto que se desea borrar.
     * @return Un mensaje de confirmación de la eliminación.
     */
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
