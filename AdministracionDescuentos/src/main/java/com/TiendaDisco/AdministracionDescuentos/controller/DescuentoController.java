package com.TiendaDisco.AdministracionDescuentos.controller;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.service.IDescuentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para la gestion de descuentos.
 * <p>Expone endpoints para registrar, consultar, actualizar y eliminar
 * descuentos, asi como asociar discos y productos a los mismos.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/descuentos")
@Tag(
        name="Descuentos",
        description = "Se administran los descuentos desde aqui"
)
public class DescuentoController {

    @Autowired
    private IDescuentoService descuentoService;

    @Operation(
            summary="Obtener todos los descuentos",
            description="Obtiene todos los discos"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Descuentos obtenidos"),
            @ApiResponse(responseCode = "500",
                    description = "Problema del servidor")
    })

    @GetMapping
    public ResponseEntity<List<DescuentoDTO>> getAllDescuentos() {
        return ResponseEntity.ok(descuentoService.getAllDescuentos());
    }

    @Operation(
            summary="obtener por id",
            description="obtiene un descuento por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Descuento obtenido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoDTO> getDescuentoId(@PathVariable Long id) {
        return ResponseEntity.ok(descuentoService.getDescuentoId(id));
    }

    /**
     * Obtiene un descuento por su nombre.
     *
     * @param nombre nombre del descuento
     * @return {@link ResponseEntity} con el {@link DescuentoDTO}
     */
    @Operation(
            summary="Obtener nombre de los descuentos",
            description="Permite obtener los nombres de los descuentos"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Nombres obtenidos"),
            @ApiResponse(responseCode = "500",
                    description = "Problema del servidor")
    })
    @GetMapping("/buscar")
    public ResponseEntity<DescuentoDTO> getDescuentoNombre(@RequestBody String nombre) {
        return ResponseEntity.ok(descuentoService.getDescuentoNombre(nombre));
    }

    @Operation(
            summary="Agregar descuento",
            description="Agrega un descuento nuevo"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Descuento creado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })

    @PostMapping
    public ResponseEntity<Descuento> postDescuento(@Valid @RequestBody Descuento d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoService.postDescuento(d));
    }

    /**
     * Actualiza los datos de un descuento existente.
     *
     * @param id identificador del descuento
     * @param d  objeto {@link Descuento} con los datos actualizados
     * @return mensaje de confirmacion
     */
    @Operation(
            summary="Actualizar descuento",
            description=""
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Descuento actualizado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> putDescuento(@PathVariable Long id, @Valid @RequestBody Descuento d) {
        return ResponseEntity.ok(descuentoService.putDescuento(id, d));
    }


    @Operation(
            summary="Borrar descuento",
            description="Permite borrar un descuento segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Descuento eliminado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDescuento(@PathVariable Long id) {
        return ResponseEntity.ok(descuentoService.deleteDescuento(id));
    }

    /**
     * Agrega un disco a un descuento existente.
     *
     * @param nombreDescuento nombre del descuento
     * @param idDisco         identificador del disco
     * @return mensaje de confirmacion
     */
    @Operation(
            summary="Agregar disco",
            description="Permite agregar un disco al descuento"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Disco agregado al descuento"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping("/{nombreDescuento}/discos/{idDisco}")
    public ResponseEntity<String> agregarDisco(@PathVariable String nombreDescuento, @PathVariable Long idDisco) {
        return ResponseEntity.ok(descuentoService.agregarDisco(nombreDescuento, idDisco));
    }

    /**
     * Quita un disco de un descuento.
     *
     * @param nombreDescuento nombre del descuento
     * @param idDisco         identificador del disco
     * @return mensaje de confirmacion
     */
    @DeleteMapping("/descuento/{nombreDescuento}")
    public ResponseEntity<String> quitarDisco(@PathVariable String nombreDescuento, @RequestBody Long idDisco) {
        return ResponseEntity.ok(descuentoService.quitarDisco(nombreDescuento, idDisco));
    }


    @PostMapping("/producto/{nombreDescuento}")
    public ResponseEntity<String> agregarProducto(@PathVariable String nombreDescuento, @RequestBody Long idProducto) {
        return ResponseEntity.ok(descuentoService.agregarProducto(nombreDescuento, idProducto));
    }


    @DeleteMapping("/producto/{nombreDescuento}")
    public ResponseEntity<String> quitarProducto(@PathVariable String nombreDescuento, @RequestBody Long idProducto) {
        return ResponseEntity.ok(descuentoService.quitarProducto(nombreDescuento, idProducto));
    }
}
