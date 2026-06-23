package com.TiendaDisco.RegistrarSede.controller;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.service.DiscoService;
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
    public String putDisco(@Valid @RequestBody Disco d, @PathVariable Long id){
        return service.putDisco(id, d);
    }

    @DeleteMapping("{id}")
    public String deleteDisco(@PathVariable Long id){
        return service.deleteDisco(id);
    }
}
