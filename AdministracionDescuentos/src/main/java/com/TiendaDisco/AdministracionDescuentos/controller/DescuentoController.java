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

    /**
     * Obtiene todos los descuentos registrados.
     *
     * @return lista de {@link DescuentoDTO}
     */
    @GetMapping
    public ResponseEntity<List<DescuentoDTO>> getAllDescuentos() {
        return ResponseEntity.ok(descuentoService.getAllDescuentos());
    }

    /**
     * Obtiene un descuento por su identificador.
     *
     * @param id identificador del descuento
     * @return {@link ResponseEntity} con el {@link DescuentoDTO}
     */
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
    @GetMapping("/buscar")
    public ResponseEntity<DescuentoDTO> getDescuentoNombre(@RequestBody String nombre) {
        return ResponseEntity.ok(descuentoService.getDescuentoNombre(nombre));
    }

    /**
     * Registra un nuevo descuento.
     *
     * @param d objeto {@link Descuento} con los datos del descuento
     * @return {@link ResponseEntity} con el descuento persistido
     */
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
    @PutMapping("/{id}")
    public ResponseEntity<String> putDescuento(@PathVariable Long id, @Valid @RequestBody Descuento d) {
        return ResponseEntity.ok(descuentoService.putDescuento(id, d));
    }

    /**
     * Elimina un descuento por su identificador.
     *
     * @param id identificador del descuento
     * @return mensaje de confirmacion
     */
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

    /**
     * Agrega un producto a un descuento existente.
     *
     * @param nombreDescuento nombre del descuento
     * @param idProducto      identificador del producto
     * @return mensaje de confirmacion
     */
    @PostMapping("/producto/{nombreDescuento}")
    public ResponseEntity<String> agregarProducto(@PathVariable String nombreDescuento, @RequestBody Long idProducto) {
        return ResponseEntity.ok(descuentoService.agregarProducto(nombreDescuento, idProducto));
    }

    /**
     * Quita un producto de un descuento.
     *
     * @param nombreDescuento nombre del descuento
     * @param idProducto      identificador del producto
     * @return mensaje de confirmacion
     */
    @DeleteMapping("/producto/{nombreDescuento}")
    public ResponseEntity<String> quitarProducto(@PathVariable String nombreDescuento, @RequestBody Long idProducto) {
        return ResponseEntity.ok(descuentoService.quitarProducto(nombreDescuento, idProducto));
    }
}
