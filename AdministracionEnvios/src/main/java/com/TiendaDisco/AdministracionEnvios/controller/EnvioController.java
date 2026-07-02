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

/**
 * Controlador REST para la gestion de envios.
 * <p>Expone endpoints para registrar, consultar, actualizar y eliminar
 * envios asociados a las ventas del sistema.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/v1/envios")
@Tag(
        name="Envios",
        description = "Se registran todos los envios"
)
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    /**
     * Registra un nuevo envio.
     *
     * @param envio objeto {@link Envio} con los datos del envio
     * @return {@link Envio} persistido
     */
    @PostMapping
    public Envio postEnvio(@RequestBody Envio envio) {
        return envioService.postEnvio(envio);
    }

    /**
     * Obtiene todos los envios registrados.
     *
     * @return lista de {@link EnvioDTO}
     */
    @GetMapping
    public List<EnvioDTO> getAllEnvios() {
        return envioService.getAllEnvios();
    }

    /**
     * Actualiza el estado de un envio.
     *
     * @param estado nuevo estado del envio
     * @param id     identificador del envio
     * @return {@link Envio} actualizado
     */
    @PutMapping("{id}")
    public Envio PutEstadoEnvio(@Valid @RequestBody EstadoEnvio estado, @PathVariable Long id) {
        return envioService.PutEstadoEnvio(estado, id);
    }

    /**
     * Actualiza la direccion de destino de un envio.
     *
     * @param direccion nueva direccion de destino
     * @param id        identificador del envio
     * @return {@link Envio} actualizado
     */
    @PutMapping("dir/{id}")
    public Envio PutDirEnvio(@Valid @RequestBody String direccion,@PathVariable Long id) {
        return envioService.PutDirEnvio(direccion, id);
    }

    /**
     * Elimina un envio por su identificador.
     *
     * @param id identificador del envio
     */
    @DeleteMapping("{id}")
    public void deleteEnvio(@PathVariable Long id) {
        envioService.deleteEnvio(id);
    }
}
