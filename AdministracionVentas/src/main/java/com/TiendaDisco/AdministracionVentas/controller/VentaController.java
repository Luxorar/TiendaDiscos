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

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para la gestion de ventas.
 * <p>Expone endpoints para registrar, consultar y eliminar ventas,
 * asi como obtener productos asociados a las mismas.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
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
    /**
     * Obtiene todas las ventas, con filtros opcionales por fecha y usuario.
     *
     * @param fechaInicio fecha de inicio del filtro (opcional)
     * @param fechaFin    fecha de fin del filtro (opcional)
     * @param usuarioId   identificador del usuario (opcional)
     * @return lista de {@link VentaDTO}
     */
    @GetMapping
    public List<VentaDTO> getAllVentas(
            @RequestParam(name = "fecha_inicio", required = false) LocalDate fechaInicio,
            @RequestParam(name = "fecha_fin", required = false) LocalDate fechaFin,
            @RequestParam(name = "usuario_id", required = false) Long usuarioId) {
        if (fechaInicio != null || fechaFin != null || usuarioId != null) {
            return ventaService.getAllVentas(fechaInicio, fechaFin, usuarioId);
        }
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
    /**
     * Registra una nueva venta en el sistema.
     *
     * @param v objeto {@link Venta} con los datos de la venta
     * @return la venta registrada como {@link VentaDTO}
     */
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
    /**
     * Obtiene una venta por su identificador.
     *
     * @param id identificador de la venta
     * @return {@link ResponseEntity} con el {@link VentaDTO}
     */
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
    /**
     * Obtiene todas las ventas de un usuario.
     *
     * @param u identificador del usuario
     * @return lista de {@link VentaDTO}
     */
    @GetMapping("user/{u}")
    public List<VentaDTO> getVentaUser(@PathVariable Long u) {
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
    /**
     * Obtiene los productos asociados a una venta.
     *
     * @param id identificador de la venta
     * @return lista de productos de la venta
     */
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
    /**
     * Elimina una venta por su identificador.
     *
     * @param id identificador de la venta a eliminar
     */
    @DeleteMapping("{id}")
    public void delVenta(@PathVariable Long id) {
        ventaService.delVenta(id);
    }
}
