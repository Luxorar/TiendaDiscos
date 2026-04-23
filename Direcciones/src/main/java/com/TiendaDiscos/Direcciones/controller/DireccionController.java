package com.TiendaDiscos.Direcciones.controller;

import com.TiendaDiscos.Direcciones.model.Direccion;
import com.TiendaDiscos.Direcciones.service.DireccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/direcciones")
public class DireccionController {
    @Autowired
    private DireccionService direccionService;

    @PostMapping()
    public String postDireccion(@Valid @RequestBody Direccion direc) {
        return direccionService.postDireccion(direc);
    }

    @GetMapping("/ciudad/{pais}/{ciudad}")
    public ArrayList<Direccion> getCiudadPais(@PathVariable String ciudad,@PathVariable String pais) {
        return direccionService.getCiudadPais(ciudad, pais);
    }

    @GetMapping("/direccion/{id}")
    public Direccion getDireccionId(@PathVariable Long id) {
        return direccionService.getDireccionId(id);
    }

    @PutMapping("/putDireccion/{id}")
    public Direccion putDireccion(@PathVariable Long id,@Valid @RequestBody Direccion direc) {
        return direccionService.putDireccion(id, direc);
    }

    @DeleteMapping("delete/{id}")
    public String DeleteDireccion(@PathVariable Long id) {
        return direccionService.DeleteDireccion(id);
    }
}
