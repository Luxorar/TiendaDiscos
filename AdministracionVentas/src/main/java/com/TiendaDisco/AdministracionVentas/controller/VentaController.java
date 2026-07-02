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
 * <p>Expone los endpoints necesarios para registrar, consultar y eliminar
 * ventas realizadas por los usuarios del sistema.</p>
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

    /**
     * Obtiene todas las ventas registradas, con filtros opcionales.
     *
     * @param fechaInicio fecha minima de la venta (opcional)
     * @param fechaFin    fecha maxima de la venta (opcional)
     * @param usuarioId   identificador del usuario (opcional)
     * @return lista de {@link VentaDTO} con los datos de cada venta
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

    /**
     * Registra una nueva venta en el sistema.
     *
     * @param v objeto {@link Venta} con los datos de la venta a registrar
     * @return {@link ResponseEntity} con el {@link VentaDTO} de la venta persistida
     */
    @PostMapping
    public ResponseEntity<VentaDTO> postVenta(@Valid @RequestBody Venta v) {
        return ResponseEntity.ok(ventaService.postVenta(v));
    }

    /**
     * Obtiene una venta por su identificador unico.
     *
     * @param id identificador de la venta
     * @return {@link ResponseEntity} con el {@link VentaDTO} correspondiente
     */
    @GetMapping("id/{id}")
    public ResponseEntity<VentaDTO> getVentaId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getVentaId(id));
    }

    /**
     * Obtiene todas las ventas asociadas a un usuario.
     *
     * @param u identificador del usuario
     * @return lista de {@link VentaDTO} del usuario
     */
    @GetMapping("user/{u}")
    public List<VentaDTO> getVentaUser(@PathVariable Long u) {
        return ventaService.getVentaUser(u);
    }

    @DeleteMapping("{id}")
    public void delVenta(@PathVariable Long id) {
        ventaService.delVenta(id);
    }
}
