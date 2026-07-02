package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la consulta de discos del carrito.
 * <p>Expone endpoints para listar todos los discos disponibles.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/carrito/discos")
@Tag(
        name = "Discos del carrito",
        description = "Consulta de discos en el carrito"
)
public class DiscoController {

    @Autowired
    private DiscoService discoService;

    /**
     * Obtiene todos los discos registrados.
     *
     * @return lista de discos
     */
    @Operation(
            summary = "Obtener todos los discos",
            description = "Obtiene todos los discos registrados en el carrito"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Discos obtenidos"),
            @ApiResponse(responseCode = "500", description = "Problema del servidor")
    })
    @GetMapping
    public List<Disco> getAll() {
        return discoService.getAllDiscos();
    }
}
