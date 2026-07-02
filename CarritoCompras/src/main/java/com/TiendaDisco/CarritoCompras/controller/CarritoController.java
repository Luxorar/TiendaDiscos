package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.service.CarritoService;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import com.TiendaDisco.CarritoCompras.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestion del carrito de compras.
 * <p>Expone endpoints para administrar carritos, incluyendo la carga
 * de discos y productos, consulta, actualizacion y eliminacion.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/carrito")
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
    @GetMapping("/{userId}")
    public CarritoDTO getByUser(@PathVariable Long userId) {
        return carritoService.getCarrito(userId);
    }

    /**
     * Crea un nuevo carrito para un usuario.
     *
     * @param carrito objeto {@link Carrito} con los datos iniciales
     * @return {@link ResponseEntity} con el carrito creado
     */
    @PostMapping
    public ResponseEntity<Carrito> create(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.postCarrito(carrito));
    }

    /**
     * Agrega un disco al carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param idDisco identificador del disco a agregar
     * @param disco   objeto {@link Disco} con los datos
     * @return {@link Disco} agregado
     */
    @PostMapping("/{userId}/discos/{idDisco}")
    public Disco addDisco(@PathVariable Long userId, @PathVariable Long idDisco,
                           @Valid @RequestBody Disco disco) {
        return discoService.postDisco(userId, idDisco, disco);
    }

    /**
     * Agrega un producto al carrito de un usuario.
     *
     * @param userId      identificador del usuario
     * @param idProducto  identificador del producto a agregar
     * @param producto    objeto {@link Producto} con los datos
     * @return {@link Producto} agregado
     */
    @PostMapping("/{userId}/productos/{idProducto}")
    public Producto addProducto(@PathVariable Long userId, @PathVariable Long idProducto,
                                 @Valid @RequestBody Producto producto) {
        return productoService.postProducto(userId, idProducto, producto);
    }

    /**
     * Elimina un disco del carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param idDisco identificador del disco a eliminar
     * @return mensaje de confirmacion
     */
    @DeleteMapping("/{userId}/discos/{idDisco}")
    public String removeDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return discoService.deleteDiscos(userId, idDisco);
    }

    /**
     * Elimina un producto del carrito de un usuario.
     *
     * @param userId      identificador del usuario
     * @param idProducto  identificador del producto a eliminar
     * @return mensaje de confirmacion
     */
    @DeleteMapping("/{userId}/productos/{idProducto}")
    public String removeProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.deleteProducto(userId, idProducto);
    }

    /**
     * Lista los productos del carrito de un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de {@link Producto} en el carrito
     */
    @GetMapping("/{userId}/productos")
    public List<Producto> listProductos(@PathVariable Long userId) {
        return productoService.getListaProducto(userId, null);
    }

    /**
     * Obtiene un producto especifico del carrito.
     *
     * @param userId      identificador del usuario
     * @param idProducto  identificador del producto
     * @return {@link Producto} solicitado
     */
    @GetMapping("/{userId}/productos/{idProducto}")
    public Producto getProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.getProducto(userId, idProducto);
    }

    /**
     * Lista los discos del carrito de un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de {@link Disco} en el carrito
     */
    @GetMapping("/{userId}/discos")
    public List<Disco> listDiscos(@PathVariable Long userId) {
        return discoService.getListaDiscos(userId);
    }

    /**
     * Obtiene un disco especifico del carrito.
     *
     * @param userId  identificador del usuario
     * @param idDisco identificador del disco
     * @return {@link Disco} solicitado
     */
    @GetMapping("/{userId}/discos/{idDisco}")
    public Disco getDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return discoService.getDisco(userId, idDisco);
    }

    /**
     * Actualiza los datos del carrito de un usuario.
     *
     * @param userId  identificador del usuario
     * @param carrito objeto {@link Carrito} con los datos a actualizar
     * @return mensaje de confirmacion
     */
    @PutMapping("/{userId}")
    public String updateCarrito(@PathVariable Long userId, @Valid @RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito, userId);
    }

    /**
     * Modifica un producto dentro del carrito.
     *
     * @param userId   identificador del usuario
     * @param producto objeto {@link Producto} con los datos actualizados
     * @return {@link Producto} modificado
     */
    @PutMapping("/{userId}/productos")
    public Producto updateProducto(@PathVariable Long userId, @Valid @RequestBody Producto producto) {
        return productoService.putProducto(userId, producto);
    }

    /**
     * Modifica un disco dentro del carrito.
     *
     * @param userId identificador del usuario
     * @param disco  objeto {@link Disco} con los datos actualizados
     * @return {@link Disco} modificado
     */
    @PutMapping("/{userId}/discos")
    public Disco updateDisco(@PathVariable Long userId, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(userId, disco);
    }

    /**
     * Elimina el carrito de un usuario.
     *
     * @param userId identificador del usuario
     */
    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable Long userId) {
        carritoService.deleteCarrito(userId);
    }
}
