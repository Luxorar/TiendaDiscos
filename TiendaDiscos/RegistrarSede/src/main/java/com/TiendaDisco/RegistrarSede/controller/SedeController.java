package com.TiendaDisco.RegistrarSede.controller;

import com.TiendaDisco.RegistrarSede.model.Sede;
import com.TiendaDisco.RegistrarSede.service.SedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Sede")
public class SedeController {
    @Autowired
    private SedeService service;

    @PostMapping
    public Sede postSede(@RequestBody Sede sede){
        return service.postSede(sede);
    }

    @GetMapping
    public List<Sede> getAllSedes(){
        return service.getAllSedes();
    }

    @GetMapping("{id}")
    public Sede getSedeId(@Valid @PathVariable Long id){
        return service.getSedeId(id);
    }

    @PutMapping("{id}")
    public String PutSede(@Valid @RequestBody Sede s, @PathVariable Long id){
        return service.putSede(id, s);
    }

    @DeleteMapping("{id}")
    public String deleteEnvio(@PathVariable Long id){
        return service.deleteSedeId(id);
    }
}
