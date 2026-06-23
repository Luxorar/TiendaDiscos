package com.TiendaDisco.AdministracionEnvios.controller;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/envios")
@Tag(
        name="Envios",
        description = "Se registran todos los envios"
)
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @Operation(
            summary="Registro de envios",
            description="Permite agregar un nuevo envio"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Envio creado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping
    public Envio postEnvio(@RequestBody Envio envio) {
        return envioService.postEnvio(envio);
    }

    @Operation(
            summary="Obtener todos los envios",
            description="Obtiene todos los envios guardados"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Envio obtenido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping
    public List<EnvioDTO> getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @Operation(
            summary="Actualizar estado de envio",
            description="Permite actualizar el estado de un envio"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Estado actualizado"),
            @ApiResponse(responseCode = "400",
                    description = "Dato invalido")
    })
    @PutMapping("{id}")
    public Envio PutEstadoEnvio(@Valid @RequestBody EstadoEnvio estado, @PathVariable Long id) {
        return envioService.PutEstadoEnvio(estado, id);
    }

    @Operation(
            summary="Actualizar direccion de envio",
            description="Permite actualizar la direccion del envio"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Direccion actualizada"),
            @ApiResponse(responseCode = "400",
                    description = "Dato invalido")
    })
    @PutMapping("dir/{id}")
    public Envio PutDirEnvio(@Valid @RequestBody String direccion,@PathVariable Long id) {
        return envioService.PutDirEnvio(direccion, id);
    }

    @Operation(
            summary="Borrar envio",
            description="Permite borrar un envio"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Envio eliminado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("{id}")
    public void deleteEnvio(@PathVariable Long id) {
        envioService.deleteEnvio(id);
    }
}
