package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
<<<<<<< HEAD
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.service.CarritoService;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import com.TiendaDisco.CarritoCompras.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
=======
import com.TiendaDisco.CarritoCompras.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 18223ab3228d24d4fdf09f1c7ec9c920c034824f
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DiscoService discoService;

    @GetMapping
    public List<CarritoDTO> getAll() {
        return carritoService.getListaCarrito();
    }

    @GetMapping("/{username}")
    public CarritoDTO getByUser(@PathVariable String username) {
        return carritoService.getCarrito(username);
    }

    @PostMapping
    public ResponseEntity<Carrito> create(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.postCarrito(carrito));
    }

    @PostMapping("/{username}/discos/{idDisco}")
    public Disco addDisco(@PathVariable String username, @PathVariable Long idDisco,
                           @Valid @RequestBody Disco disco) {
        return discoService.postDisco(username, idDisco, disco);
    }

    @PostMapping("/{username}/productos/{idProducto}")
    public Producto addProducto(@PathVariable String username, @PathVariable Long idProducto,
                                 @Valid @RequestBody Producto producto) {
        return productoService.postProducto(username, idProducto, producto);
    }

    @DeleteMapping("/{username}/discos/{idDisco}")
    public String removeDisco(@PathVariable String username, @PathVariable Long idDisco) {
        return discoService.deleteDiscos(username, idDisco);
    }

    @DeleteMapping("/{username}/productos/{idProducto}")
    public String removeProducto(@PathVariable String username, @PathVariable Long idProducto) {
        return productoService.deleteProducto(username, idProducto);
    }

    @GetMapping("/{username}/productos")
    public List<Producto> listProductos(@PathVariable String username) {
        return productoService.getListaProducto(username, null);
    }

    @GetMapping("/{username}/productos/{idProducto}")
    public Producto getProducto(@PathVariable String username, @PathVariable Long idProducto) {
        return productoService.getProducto(username, idProducto);
    }

    @GetMapping("/{username}/discos")
    public List<Disco> listDiscos(@PathVariable String username) {
        return discoService.getListaDiscos(username);
    }

    @GetMapping("/{username}/discos/{idDisco}")
    public Disco getDisco(@PathVariable String username, @PathVariable Long idDisco) {
        return discoService.getDisco(username, idDisco);
    }

    @PutMapping("/{username}")
    public String updateCarrito(@PathVariable String username, @Valid @RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito, username);
    }

    @PutMapping("/{username}/productos")
    public Producto updateProducto(@PathVariable String username, @Valid @RequestBody Producto producto) {
        return productoService.putProducto(username, producto);
    }

    @PutMapping("/{username}/discos")
    public Disco updateDisco(@PathVariable String username, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(username, disco);
    }

    @DeleteMapping("/{username}")
    public void deleteByUser(@PathVariable String username) {
        carritoService.deleteCarrito(username);
    }
=======
@RequestMapping("api/v1/Carrito")
public class CarritoController {
    @Autowired
    private CarritoService service;

    @PostMapping
    public Carrito postCarrito(@RequestBody Carrito c){return service.postCarrito(c);}

    @GetMapping("/lista")
    public List<CarritoDTO> getListCarrito(){return service.getListaCarrito();}

    @GetMapping("/get/{user}")
    public CarritoDTO getCarrito(@Valid @PathVariable String user){return service.getCarrito(user);}

    @PutMapping("/update/{usuario}")
    public String updateCarrito(@Valid @RequestBody Carrito c, @PathVariable String usuario){return service.updateCarrito(c, usuario);}
>>>>>>> 18223ab3228d24d4fdf09f1c7ec9c920c034824f
}
