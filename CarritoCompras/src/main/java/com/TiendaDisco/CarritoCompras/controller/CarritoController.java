package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.dto.CarritoDiscoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.service.CarritoDiscoService;
import com.TiendaDisco.CarritoCompras.service.CarritoService;
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
        name = "Carrito",
        description = "Se administra el carrito de compras desde aqui"
)
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CarritoDiscoService carritoDiscoService;

    @Operation(summary = "Obtener todos los carritos")
    @GetMapping
    public List<CarritoDTO> getAll() {
        return carritoService.getListaCarrito();
    }

    @Operation(summary = "Obtener carrito por usuario")
    @GetMapping("/{userId}")
    public CarritoDTO getByUser(@PathVariable Long userId) {
        return carritoService.getCarrito(userId);
    }

    @Operation(summary = "Crear carrito")
    @PostMapping
    public ResponseEntity<Carrito> create(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.postCarrito(carrito));
    }

    @Operation(summary = "Agregar disco al carrito")
    @PostMapping("/{userId}/discos/{idDisco}")
    public CarritoDiscoDTO addDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return carritoDiscoService.addDisco(userId, idDisco);
    }

    @Operation(summary = "Listar discos del carrito")
    @GetMapping("/{userId}/discos")
    public List<CarritoDiscoDTO> listDiscos(@PathVariable Long userId) {
        return carritoDiscoService.getListaDiscos(userId);
    }

    @Operation(summary = "Actualizar cantidad de disco en carrito")
    @PutMapping("/{userId}/discos/{idDisco}")
    public CarritoDiscoDTO updateDiscoQty(@PathVariable Long userId, @PathVariable Long idDisco,
                                           @RequestParam int qty) {
        return carritoDiscoService.updateQty(userId, idDisco, qty);
    }

    @Operation(summary = "Eliminar disco del carrito (decrementa qty o elimina)")
    @DeleteMapping("/{userId}/discos/{idDisco}")
    public String removeDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return carritoDiscoService.deleteDisco(userId, idDisco);
    }

    @Operation(summary = "Vaciar todos los discos del carrito")
    @DeleteMapping("/{userId}/discos")
    public String clearDiscos(@PathVariable Long userId) {
        carritoDiscoService.clearDiscos(userId);
        return "Carrito vaciado";
    }

    @Operation(summary = "Agregar producto al carrito")
    @PostMapping("/{userId}/productos/{idProducto}")
    public Producto addProducto(@PathVariable Long userId, @PathVariable Long idProducto,
                                 @Valid @RequestBody Producto producto) {
        return productoService.postProducto(userId, idProducto, producto);
    }

    @Operation(summary = "Eliminar producto del carrito")
    @DeleteMapping("/{userId}/productos/{idProducto}")
    public String removeProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.deleteProducto(userId, idProducto);
    }

    @Operation(summary = "Listar productos del carrito")
    @GetMapping("/{userId}/productos")
    public List<Producto> listProductos(@PathVariable Long userId) {
        return productoService.getListaProducto(userId, null);
    }

    @Operation(summary = "Obtener producto del carrito")
    @GetMapping("/{userId}/productos/{idProducto}")
    public Producto getProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.getProducto(userId, idProducto);
    }

    @Operation(summary = "Actualizar descuento del carrito")
    @PutMapping("/{userId}")
    public String updateCarrito(@PathVariable Long userId, @Valid @RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito, userId);
    }

    @Operation(summary = "Modificar producto en carrito")
    @PutMapping("/{userId}/productos")
    public Producto updateProducto(@PathVariable Long userId, @Valid @RequestBody Producto producto) {
        return productoService.putProducto(userId, producto);
    }

    @Operation(summary = "Eliminar carrito completo")
    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable Long userId) {
        carritoService.deleteCarrito(userId);
    }
}
