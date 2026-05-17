package com.TiendaDisco.AdministracionEnvios.controller;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/envios")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @PostMapping
    public Envio postEnvio(@RequestBody Envio envio) {
        return envioService.postEnvio(envio);
    }

    @GetMapping
    public List<Envio> getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @PutMapping("{id}")
    public Envio PutEstadoEnvio(@Valid @RequestBody EstadoEnvio estado, @PathVariable Long id) {
        return envioService.PutEstadoEnvio(estado, id);
    }

    @PutMapping("dir/{id}")
    public Envio PutDirEnvio(@Valid @RequestBody String direccion,@PathVariable Long id) {
        return envioService.PutDirEnvio(direccion, id);
    }

    @DeleteMapping("{id}")
    public void deleteEnvio(@PathVariable Long id) {
        envioService.deleteEnvio(id);
    }
}
