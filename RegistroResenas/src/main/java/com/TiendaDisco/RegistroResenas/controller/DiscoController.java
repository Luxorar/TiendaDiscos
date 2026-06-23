package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.DiscoDTO;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.service.DiscoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Disco")
public class DiscoController {
    @Autowired
    private DiscoService service;

    @PostMapping
    public Disco postDisco(@RequestBody Disco disco){
        return service.postDisco(disco);
    }

    @GetMapping
    public List<DiscoDTO> getAllDiscos(){
        return service.getAllDiscos();
    }

    @GetMapping("{id}")
    public DiscoDTO getDiscoId(@Valid @PathVariable Long id){
        return service.getDiscoId(id);
    }

    @PutMapping("{id}")
    public String putDisco(@PathVariable Long id, @RequestBody Disco disco){
        return service.putDisco(id, disco);
    }

    @DeleteMapping("{id}")
    public String deleteDiscoId(@PathVariable Long id){
        return service.deleteDisco(id);
    }
}
