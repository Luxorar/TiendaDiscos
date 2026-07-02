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

/**
 * Controlador REST para la gestion del carrito de compras.
 * <p>Expone endpoints para administrar carritos, productos y discos
 * asociados a cada usuario.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
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
    private DiscoService discoService;

    /**
     * Obtiene todos los carritos registrados.
     *
     * @return lista de {@link CarritoDTO}
     */
    @Operation(
            summary = "Obtener todos los carritos",
            description = "Obtiene todos los carritos de compras"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carritos obtenidos"),
            @ApiResponse(responseCode = "500", description = "Problema del servidor")
    })
    @GetMapping
    public List<CarritoDTO> getAll() {
        return carritoService.getListaCarrito();
    }

    /**
     * Obtiene el carrito de un usuario por su identificador.
     *
     * @param userId identificador del usuario
     * @return {@link CarritoDTO} del usuario
     */
    @Operation(
            summary = "Obtener carrito por usuario",
            description = "Obtiene el carrito de un usuario segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrito obtenido"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{userId}")
    public CarritoDTO getByUser(@PathVariable Long userId) {
        return carritoService.getCarrito(userId);
    }

    /**
     * Crea un nuevo carrito de compras.
     *
     * @param carrito objeto {@link Carrito} con los datos iniciales
     * @return el carrito creado
     */
    @Operation(
            summary = "Crear carrito",
            description = "Permite crear un nuevo carrito de compras"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Carrito creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<Carrito> create(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.postCarrito(carrito));
    }

    /**
     * Agrega un disco al carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param idDisco identificador del disco
     * @param disco   datos del disco a agregar
     * @return el disco agregado
     */
    @Operation(
            summary = "Agregar disco al carrito",
            description = "Permite agregar un disco al carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disco agregado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping("/{userId}/discos/{idDisco}")
    public Disco addDisco(@PathVariable Long userId, @PathVariable Long idDisco,
                           @Valid @RequestBody Disco disco) {
        return discoService.postDisco(userId, idDisco, disco);
    }

    /**
     * Agrega un producto al carrito de un usuario.
     *
     * @param userId      identificador del usuario
     * @param idProducto  identificador del producto
     * @param producto    datos del producto a agregar
     * @return el producto agregado
     */
    @Operation(
            summary = "Agregar producto al carrito",
            description = "Permite agregar un producto al carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto agregado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping("/{userId}/productos/{idProducto}")
    public Producto addProducto(@PathVariable Long userId, @PathVariable Long idProducto,
                                 @Valid @RequestBody Producto producto) {
        return productoService.postProducto(userId, idProducto, producto);
    }

    /**
     * Elimina un disco del carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param idDisco identificador del disco
     * @return mensaje de confirmacion
     */
    @Operation(
            summary = "Eliminar disco del carrito",
            description = "Permite eliminar un disco del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disco eliminado"),
            @ApiResponse(responseCode = "404", description = "Disco no encontrado")
    })
    @DeleteMapping("/{userId}/discos/{idDisco}")
    public String removeDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return discoService.deleteDiscos(userId, idDisco);
    }

    /**
     * Elimina un producto del carrito de un usuario.
     *
     * @param userId      identificador del usuario
     * @param idProducto  identificador del producto
     * @return mensaje de confirmacion
     */
    @Operation(
            summary = "Eliminar producto del carrito",
            description = "Permite eliminar un producto del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{userId}/productos/{idProducto}")
    public String removeProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.deleteProducto(userId, idProducto);
    }

    /**
     * Obtiene todos los productos del carrito de un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de productos
     */
    @Operation(
            summary = "Listar productos del carrito",
            description = "Obtiene todos los productos del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Productos obtenidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{userId}/productos")
    public List<Producto> listProductos(@PathVariable Long userId) {
        return productoService.getListaProducto(userId, null);
    }

    /**
     * Obtiene un producto especifico del carrito de un usuario.
     *
     * @param userId      identificador del usuario
     * @param idProducto  identificador del producto
     * @return el producto solicitado
     */
    @Operation(
            summary = "Obtener producto del carrito",
            description = "Obtiene un producto especifico del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto obtenido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{userId}/productos/{idProducto}")
    public Producto getProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.getProducto(userId, idProducto);
    }

    /**
     * Obtiene todos los discos del carrito de un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de discos
     */
    @Operation(
            summary = "Listar discos del carrito",
            description = "Obtiene todos los discos del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Discos obtenidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{userId}/discos")
    public List<Disco> listDiscos(@PathVariable Long userId) {
        return discoService.getListaDiscos(userId);
    }

    /**
     * Obtiene un disco especifico del carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param idDisco identificador del disco
     * @return el disco solicitado
     */
    @Operation(
            summary = "Obtener disco del carrito",
            description = "Obtiene un disco especifico del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disco obtenido"),
            @ApiResponse(responseCode = "404", description = "Disco no encontrado")
    })
    @GetMapping("/{userId}/discos/{idDisco}")
    public Disco getDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return discoService.getDisco(userId, idDisco);
    }

    /**
     * Actualiza el descuento del carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param carrito objeto con el descuento actualizado
     * @return mensaje de confirmacion
     */
    @Operation(
            summary = "Actualizar carrito",
            description = "Permite actualizar el descuento del carrito de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrito actualizado"),
            @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @PutMapping("/{userId}")
    public String updateCarrito(@PathVariable Long userId, @Valid @RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito, userId);
    }

    /**
     * Modifica un producto dentro del carrito de un usuario.
     *
     * @param userId   identificador del usuario
     * @param producto objeto con los datos actualizados del producto
     * @return el producto modificado
     */
    @Operation(
            summary = "Modificar producto en carrito",
            description = "Permite modificar un producto existente en el carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto modificado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{userId}/productos")
    public Producto updateProducto(@PathVariable Long userId, @Valid @RequestBody Producto producto) {
        return productoService.putProducto(userId, producto);
    }

    /**
     * Modifica un disco dentro del carrito de un usuario.
     *
     * @param userId identificador del usuario
     * @param disco  objeto con los datos actualizados del disco
     * @return el disco modificado
     */
    @Operation(
            summary = "Modificar disco en carrito",
            description = "Permite modificar un disco existente en el carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disco modificado"),
            @ApiResponse(responseCode = "404", description = "Disco no encontrado")
    })
    @PutMapping("/{userId}/discos")
    public Disco updateDisco(@PathVariable Long userId, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(userId, disco);
    }

    /**
     * Elimina el carrito completo de un usuario.
     *
     * @param userId identificador del usuario
     */
    @Operation(
            summary = "Eliminar carrito",
            description = "Permite eliminar el carrito completo de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrito eliminado"),
            @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable Long userId) {
        carritoService.deleteCarrito(userId);
    }
}
