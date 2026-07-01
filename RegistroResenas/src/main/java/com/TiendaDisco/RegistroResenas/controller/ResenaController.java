package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.service.ResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para la gestion de las resenas.
 * Se encarga de procesar las peticiones http y delegar la logica al servicio correspondiente.
 * * Ruta base: /api/v1/Resena
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/v1/Resena")
@Tag(
        name="Resenas",
        description = "Se registran las resenas de los discos"
)
public class ResenaController {
    @Autowired
    private ResenaService service;

    /**
     * Endpoint para registrar una nueva resena en el sistema.
     * * @param resena Objeto con los datos de la resena a registrar
     * @return La resena persistida
     */
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

    /**
     * Endpoint para obtener todas las resenas registradas.
     * * @return Una respuesta HTTP 200 con la lista completa de discos en formato DTO.
     */
    @Operation(
            summary="Obtener todas las resenas",
            description="Retorna una lista con todas las resenas registradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description = "Obtencion exitosa")
    })
    @GetMapping
    public List<ResenaDTO> getAllResenas(){
        return service.getAllResenas();
    }

    /**
     * Endpoint para buscar la información detallada de una resena especifica.
     * * @param id El identificador único de la resena enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO de la resena, o 400/404 si hay un error.
     */
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
    public ResenaDTO getResenaId(@Valid @PathVariable Long id){
        return service.getResenaId(id);
    }

    /**
     * Endpoint para eliminar una resena del catálogo.
     * * @param id El identificador de la resena que se desea borrar.
     * @return Un mensaje de confirmación de la eliminación.
     */
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
