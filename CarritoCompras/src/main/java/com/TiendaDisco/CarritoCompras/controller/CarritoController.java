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

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DiscoService discoService;

    //==================OBTIENE TODOS LOS CARRITOS================================
    @GetMapping
    public List<CarritoDTO> getAll() {
        return carritoService.getListaCarrito();
    }

    //==================OBTIENE CARRITO POR USUARIO================================
    @GetMapping("/{userId}")
    public CarritoDTO getByUser(@PathVariable Long userId) {
        return carritoService.getCarrito(userId);
    }

    //==================CREA UN CARRITO================================
    @PostMapping
    public ResponseEntity<Carrito> create(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.postCarrito(carrito));
    }

    //==================AGREGA DISCO AL CARRITO================================
    @PostMapping("/{userId}/discos/{idDisco}")
    public Disco addDisco(@PathVariable Long userId, @PathVariable Long idDisco,
                           @Valid @RequestBody Disco disco) {
        return discoService.postDisco(userId, idDisco, disco);
    }

    //==================AGREGA PRODUCTO AL CARRITO================================
    @PostMapping("/{userId}/productos/{idProducto}")
    public Producto addProducto(@PathVariable Long userId, @PathVariable Long idProducto,
                                 @Valid @RequestBody Producto producto) {
        return productoService.postProducto(userId, idProducto, producto);
    }

    //==================ELIMINA DISCO DEL CARRITO================================
    @DeleteMapping("/{userId}/discos/{idDisco}")
    public String removeDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return discoService.deleteDiscos(userId, idDisco);
    }

    //==================ELIMINA PRODUCTO DEL CARRITO================================
    @DeleteMapping("/{userId}/productos/{idProducto}")
    public String removeProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.deleteProducto(userId, idProducto);
    }

    //==================OBTIENE PRODUCTOS DEL CARRITO================================
    @GetMapping("/{userId}/productos")
    public List<Producto> listProductos(@PathVariable Long userId) {
        return productoService.getListaProducto(userId, null);
    }

    //==================OBTIENE PRODUCTO DEL CARRITO================================
    @GetMapping("/{userId}/productos/{idProducto}")
    public Producto getProducto(@PathVariable Long userId, @PathVariable Long idProducto) {
        return productoService.getProducto(userId, idProducto);
    }

    //==================OBTIENE DISCOS DEL CARRITO================================
    @GetMapping("/{userId}/discos")
    public List<Disco> listDiscos(@PathVariable Long userId) {
        return discoService.getListaDiscos(userId);
    }

    //==================OBTIENE DISCO DEL CARRITO================================
    @GetMapping("/{userId}/discos/{idDisco}")
    public Disco getDisco(@PathVariable Long userId, @PathVariable Long idDisco) {
        return discoService.getDisco(userId, idDisco);
    }

    //==================ACTUALIZA CARRITO================================
    @PutMapping("/{userId}")
    public String updateCarrito(@PathVariable Long userId, @Valid @RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito, userId);
    }

    //==================MODIFICA PRODUCTO EN CARRITO================================
    @PutMapping("/{userId}/productos")
    public Producto updateProducto(@PathVariable Long userId, @Valid @RequestBody Producto producto) {
        return productoService.putProducto(userId, producto);
    }

    //==================MODIFICA DISCO EN CARRITO================================
    @PutMapping("/{userId}/discos")
    public Disco updateDisco(@PathVariable Long userId, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(userId, disco);
    }

    //==================ELIMINA CARRITO================================
    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable Long userId) {
        carritoService.deleteCarrito(userId);
    }
}
