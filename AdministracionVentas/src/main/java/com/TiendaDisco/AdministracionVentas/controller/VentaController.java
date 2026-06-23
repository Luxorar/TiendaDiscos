package com.TiendaDisco.AdministracionVentas.controller;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import com.TiendaDisco.AdministracionVentas.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ventas")
@Tag(
        name="Ventas",
        description = "Se registran todas las ventas"
)
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(
            summary="Obtener todas las ventas",
            description="Permite obtener todas las ventas"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Ventas obtenidas"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping
    public List<VentaDTO> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @Operation(
            summary="Agregar ventas",
            description="Permite agregar una venta"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Venta creada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<VentaDTO> postVenta(@Valid @RequestBody Venta v) {
        return ResponseEntity.ok(ventaService.postVenta(v));
    }

    @Operation(
            summary="Obtener venta por id",
            description="Permite obtener una venta por su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Venta obtenida"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("id/{id}")
    public ResponseEntity<VentaDTO> getVentaId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getVentaId(id));
    }

    @Operation(
            summary="Obtener ventas por usuario",
            description="Permite obtener las ventas del usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Ventas obtenidas"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("user/{u}")
    public List<VentaDTO> getVentaUser(@PathVariable String u) {
        return ventaService.getVentaUser(u);
    }

    @Operation(
            summary="Obtener productos",
            description="Permite obtener los productos segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Producto obtenido"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("productos/{id}")
    public List<Producto> getProductoReciboId(@PathVariable Long id) {
        return ventaService.getProductoReciboId(id);
    }


    @Operation(
            summary="Eliminar venta",
            description="Permite eliminar una venta"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Venta eliminada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("{id}")
    public void delVenta(@PathVariable Long id) {
        ventaService.delVenta(id);
    }
}
