package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
}
