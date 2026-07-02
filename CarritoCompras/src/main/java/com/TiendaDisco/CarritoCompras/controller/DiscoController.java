package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @deprecated
 * esta clase fue creada antes de la comunicación de los microservicios, eliminar en versionaes futuras
 */
@RestController
@RequestMapping("/api/v1/carrito/discosp")
public class DiscoController {

    @Autowired
    private DiscoService discoService;

    /**
     * Obtiene todos los discos del sistema.
     *
     * @return lista de {@link Disco}
     */
    @GetMapping
    public List<Disco> getAll() {
        return discoService.getAllDiscos();
    }
}
