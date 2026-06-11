package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.service.ResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Resena")
@Tag(
        name="Resenas",
        description = "Se registran las resenas de los discos"
)
public class ResenaController {
    @Autowired
    private ResenaService service;

    @Operation(
            summary="Registro de una resena",
            description="Permite agregar una nueva resena activa de un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Resena creada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping
    public Resena postResena(@RequestBody Resena resena){
        return service.postResena(resena);
    }

    @Operation(
            summary="Obtencion de resena con id",
            description="Obtiene una resena en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description = "Obtencion exitosa"),
            @ApiResponse(responseCode="400",
                    description = "Datos invalidos")
    })
    @GetMapping("{id}")
    public Resena getResenaId(@Valid @PathVariable Long id){
        return service.getResenaId(id);
    }

    @Operation(
            summary="Eliminar resena",
            description="Eliminar resena en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Eliminacion exitosa"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @DeleteMapping("{id}")
    public String deleteResenaId(@PathVariable Long id){
        return service.deleteResena(id);
    }
}
