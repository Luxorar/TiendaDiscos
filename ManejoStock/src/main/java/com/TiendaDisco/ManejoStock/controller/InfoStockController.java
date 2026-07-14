package com.TiendaDisco.ManejoStock.controller;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.model.infoStock;
import com.TiendaDisco.ManejoStock.service.IInfoStockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para la gestion de la info del stock.
 * Se encarga de procesar las peticiones http y delegar la logica al servicio correspondiente.
 * * Ruta base: /api/v1/stock
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/stock")
public class InfoStockController {

    @Autowired
    private IInfoStockService stockService;

    /**
     * Obtiene la lista completa de todo el stock registrado en el sistema.
     * * @return Un objeto ResponseEntity que contiene una lista de {@link InfoStockDTO} con estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<InfoStockDTO>> getAllInfoStock() {
        return ResponseEntity.ok(stockService.getAllInfoStock());
    }

    /**
     * Registra un nuevo inventario o stock en la base de datos.
     * * @param stock El objeto {@link infoStock} con los datos a registrar, validado previamente.
     * @return Un objeto ResponseEntity con la entidad guardada y estado HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<infoStock> postInfoStock(@Valid @RequestBody infoStock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.postInfoStock(stock));
    }

    /**
     * Busca y retorna la información de stock asociada a un identificador único.
     * * @param id El identificador único del registro de stock.
     * @return Un objeto ResponseEntity que contiene el {@link InfoStockDTO} encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InfoStockDTO> getInfoID(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getInfoID(id));
    }

    /**
     * Obtiene la información de stock filtrada por el nombre de un producto específico.
     * * @param nombreProducto El nombre exacto del producto a buscar.
     * @return Un objeto ResponseEntity que contiene el {@link InfoStockDTO} del producto solicitado.
     */
    @GetMapping("/producto/{nombreProducto}")
    public ResponseEntity<InfoStockDTO> getProductoInfo(@PathVariable String nombreProducto) {
        return ResponseEntity.ok(stockService.getProductoInfo(nombreProducto));
    }

    /**
     * Obtiene una lista con todo el stock disponible en una sede específica.
     * * @param nombreSede El nombre de la sucursal o sede a consultar.
     * @return Un objeto ResponseEntity con la lista de {@link InfoStockDTO} pertenecientes a la sede.
     */
    @GetMapping("/sede/{nombreSede}")
    public ResponseEntity<List<InfoStockDTO>> getSedeInfo(@PathVariable String nombreSede) {
        return ResponseEntity.ok(stockService.getSedeInfo(nombreSede));
    }

    /**
     * Obtiene el stock total disponible de un disco por su ID, sumando todas las sedes.
     * * @param discoId El identificador del disco.
     * @return Un objeto ResponseEntity con la cantidad total de stock.
     */
    @GetMapping("/disco/{discoId}")
    public ResponseEntity<Integer> getStockByDiscoId(@PathVariable Long discoId) {
        return ResponseEntity.ok(stockService.getStockTotalByDiscoId(discoId));
    }

    /**
     * Actualiza únicamente la cantidad disponible en el stock de un registro específico.
     * * @param id El identificador único del registro de stock a modificar.
     * @param nuevoStock La nueva cantidad numérica que reemplazará al stock actual.
     * @return Un objeto ResponseEntity con un mensaje de confirmación en formato texto.
     */
    @PutMapping("/{id}/cantidad")
    public ResponseEntity<String> putStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        return ResponseEntity.ok(stockService.putStock(id, nuevoStock));
    }

    /**
     * Elimina un registro de stock de la base de datos a partir de su ID.
     * * @param id El identificador único del registro que se desea eliminar.
     * @return Un objeto ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInfo(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.deleteInfo(id));
    }
}