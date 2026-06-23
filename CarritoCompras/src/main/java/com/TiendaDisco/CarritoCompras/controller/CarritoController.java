package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.service.CarritoService;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import com.TiendaDisco.CarritoCompras.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito")
@Tag(
        name="Carrito",
        description = "Se registran todos los carritos"
)
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DiscoService discoService;

    @Operation(
            summary="Obtener todos los carritos",
            description="Permite obtener todos los carritos"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Carritos obtenidos"),
            @ApiResponse(responseCode = "500",
                    description = "Error del servidor")
    })
    @GetMapping
    public List<CarritoDTO> getAll() {
        return carritoService.getListaCarrito();
    }

    @Operation(
            summary="Obtener por usuario",
            description="Permite obtener por usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Carrito obtenido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{username}")
    public CarritoDTO getByUser(@PathVariable String username) {
        return carritoService.getCarrito(username);
    }

    @Operation(
            summary="Crear carrito",
            description="Permite agregar un nuevo carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="carrito creado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<Carrito> create(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.postCarrito(carrito));
    }

    @Operation(
            summary="Agregar disco",
            description="Permite agregar un disco al carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Disco agregado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping("/{username}/discos/{idDisco}")
    public Disco addDisco(@PathVariable String username, @PathVariable Long idDisco,
                           @Valid @RequestBody Disco disco) {
        return discoService.postDisco(username, idDisco, disco);
    }

    @Operation(
            summary="Agregar producto",
            description="Permite agregar un producto al carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto agregado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping("/{username}/productos/{idProducto}")
    public Producto addProducto(@PathVariable String username, @PathVariable Long idProducto,
                                 @Valid @RequestBody Producto producto) {
        return productoService.postProducto(username, idProducto, producto);
    }

    @Operation(
            summary="Remover disco",
            description="Permite remover un disco del carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Disco removido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("/{username}/discos/{idDisco}")
    public String removeDisco(@PathVariable String username, @PathVariable Long idDisco) {
        return discoService.deleteDiscos(username, idDisco);
    }

    @Operation(
            summary="Remover producto",
            description="Permite remover un producto del carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Disco removido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("/{username}/productos/{idProducto}")
    public String removeProducto(@PathVariable String username, @PathVariable Long idProducto) {
        return productoService.deleteProducto(username, idProducto);
    }

    @Operation(
            summary="Obtener lista de productos",
            description="Permite obtener la lista de productos"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Productos obtenidos"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{username}/productos")
    public List<Producto> listProductos(@PathVariable String username) {
        return productoService.getListaProducto(username, null);
    }

    @Operation(
            summary="Obtener productos por id",
            description="Permite obtener productos en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto obtenido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{username}/productos/{idProducto}")
    public Producto getProducto(@PathVariable String username, @PathVariable Long idProducto) {
        return productoService.getProducto(username, idProducto);
    }

    @Operation(
            summary="Obtener lista de discos",
            description="Permite obtener la lista de discos"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Discos obtenidos"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{username}/discos")
    public List<Disco> listDiscos(@PathVariable String username) {
        return discoService.getListaDiscos(username);
    }

    @Operation(
            summary="Obtener disco por id",
            description="Permite obtener discos en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Disco obtenido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{username}/discos/{idDisco}")
    public Disco getDisco(@PathVariable String username, @PathVariable Long idDisco) {
        return discoService.getDisco(username, idDisco);
    }
    @Operation(
            summary="Actualizar carrito",
            description="Permite actualizar el carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Carrito actualizado"),
            @ApiResponse(responseCode = "400",
                    description = "Dato invalido")
    })
    @PutMapping("/{username}")
    public String updateCarrito(@PathVariable String username, @Valid @RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito, username);
    }

    @Operation(
            summary="Actualizar producto",
            description="Permite actualizar un producto"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto actualizado"),
            @ApiResponse(responseCode = "400",
                    description = "Dato invalido")
    })
    @PutMapping("/{username}/productos")
    public Producto updateProducto(@PathVariable String username, @Valid @RequestBody Producto producto) {
        return productoService.putProducto(username, producto);
    }

    @Operation(
            summary="Actualizar disco",
            description="Permite actualizar un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Disco actualizado"),
            @ApiResponse(responseCode = "400",
                    description = "Dato invalido")
    })
    @PutMapping("/{username}/discos")
    public Disco updateDisco(@PathVariable String username, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(username, disco);
    }

    @Operation(
            summary="Borrar Carrito",
            description="Permite borrar un carrito en base a su usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Carrito eliminado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("/{username}")
    public void deleteByUser(@PathVariable String username) {
        carritoService.deleteCarrito(username);
    }
}
