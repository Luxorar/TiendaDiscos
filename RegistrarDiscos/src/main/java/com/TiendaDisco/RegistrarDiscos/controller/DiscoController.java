package com.TiendaDisco.RegistrarDiscos.controller;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.service.IDiscoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(
        name="Discos",
        description="Se administran los discos"
)
public class DiscoController {

    @Autowired
    private IDiscoService discoService;

    @Operation(
            summary="Registro de un disco",
            description="Permite agregar un nuevo disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Disco creado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
})
    //==================REGISTRA UN DISCO================================
    @PostMapping
    public Disco registrarDisco(@Valid @RequestBody Disco disco) {
        return discoService.postDisco(disco);
    }

    @Operation(
            summary="Obtencion de un disco por id",
            description ="Obtiene un disco en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Obtencion exitosa"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    //==================OBTIENE DISCO POR ID================================
    @GetMapping("/{id}")
    public ResponseEntity<DiscoDTO> obtenerDiscoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(discoService.getDiscoId(id));
    }

    @Operation(
            summary="Actualizar disco",
            description = "Actualiza los datos de un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Actualizacion completa"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos"

            )
    })
    //==================MODIFICA UN DISCO================================
    @PutMapping("/{id}")
    public String actualizarDisco(@PathVariable Long id, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(id, disco);
    }

    @Operation(
            summary="Eliminar disco",
            description = "Elimina un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Eloiminacion exitosa"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    //==================ELIMINA UN DISCO================================
    @DeleteMapping("/{id}")
    public String eliminarDisco(@PathVariable Long id) {
        return discoService.deleteDisco(id);
    }

    //==================OBTIENE TODOS LOS DISCOS================================
    @GetMapping
    public ResponseEntity<List<DiscoDTO>> obtenerTodosLosDiscos() {
        return ResponseEntity.ok(discoService.getAllDiscos());
    }
}
