package com.TiendaDisco.ManejoStock.controller;

import com.TiendaDisco.ManejoStock.model.infoStock;
import com.TiendaDisco.ManejoStock.service.IInfoStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@Tag(
        name="Stock",
        description = "Se registra el stock de los productos"
)
public class InfoStockController {

    @Autowired
    private IInfoStockService stockService;

    @Operation(
            summary="Registrar informacion",
            description="Permite agregar nueva informacion de stock"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Informacion creada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<infoStock> postInfoStock(@Valid @RequestBody infoStock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.postInfoStock(stock));
    }

    @Operation(
            summary="Obtener por id",
            description="Permite obtener informacion por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Informacion obtenida"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{id}")
    public ResponseEntity<infoStock> getInfoID(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getInfoID(id));
    }

    @Operation(
            summary="Obtener informacion producto",
            description="Permite obtener informacion de un producto en base a su nombre"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Informacion obtenida"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/producto/{nombreProducto}")
    public ResponseEntity<infoStock> getProductoInfo(@PathVariable String nombreProducto) {
        return ResponseEntity.ok(stockService.getProductoInfo(nombreProducto));
    }

    @Operation(
            summary="obtener informacion sede",
            description="Permite obtener informacion en base a su sede"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Informacion obtenida"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/sede/{nombreSede}")
    public ResponseEntity<List<infoStock>> getSedeInfo(@PathVariable String nombreSede) {
        return ResponseEntity.ok(stockService.getSedeInfo(nombreSede));
    }

    @Operation(
            summary="Actualizar nombre producto",
            description="Permite actualizar el nombre de un producto en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Nombre actualizado"),
            @ApiResponse(responseCode = "500",
                    description = "Error del servidor")
    })
    @PutMapping("producto/{id}")
    public ResponseEntity<String> putNombreProducto(@PathVariable Long id, @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(stockService.putNombreProducto(id, nuevoNombre));
    }

    @Operation(
            summary="Actualizar Stock",
            description="Permite actualizar el stock en base a id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Stock actualizado"),
            @ApiResponse(responseCode = "500",
                    description = "Error del servidor")
    })
    @PutMapping("Stock/{id}")
    public ResponseEntity<String> putStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        return ResponseEntity.ok(stockService.putStock(id, nuevoStock));
    }

    @Operation(
            summary="Actualizar sede",
            description="Permite actualizar la sede segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Sede actualizada"),
            @ApiResponse(responseCode = "500",
                    description = "Error del servidor")
    })
    @PutMapping("sede/{id}")
    public ResponseEntity<String> putSede(@PathVariable Long id, @RequestParam String nombreSede) {
        return ResponseEntity.ok(stockService.putSede(id, nombreSede));
    }

    @Operation(
            summary="Borrar informacion",
            description="Borra la informacion en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Informacion borrada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInfo(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.deleteInfo(id));
    }
}