package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Resena")
public class ResenaController {
    @Autowired
    private ResenaService service;

    @PostMapping
    public Resena postResena(@RequestBody Resena resena){
        return service.postResena(resena);
    }

    @GetMapping
    public List<Resena> getAllResenas(){
        return service.getAllResenas();
    }

    @GetMapping("{id}")
    public Resena getResenaId(@Valid @PathVariable Long id){
        return service.getResenaId(id);
    }

    @DeleteMapping("{id}")
    public String deleteResenaId(@PathVariable Long id){
        return service.deleteResena(id);
    }
}
