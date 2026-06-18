package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito/discos")
public class DiscoController {

    @Autowired
    private DiscoService discoService;

    @GetMapping
    public List<Disco> getAll() {
        return discoService.getAllDiscos();
    }
}
