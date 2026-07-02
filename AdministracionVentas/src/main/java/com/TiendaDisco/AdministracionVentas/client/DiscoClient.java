package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.dto.DiscoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para la comunicacion con el microservicio de Discos.
 * <p>Permite consultar informacion de discos registrados en el
 * modulo {@code RegistrarDiscos}.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@FeignClient(name="DiscoClient", url= "http://registrar-discos:8086")
public interface DiscoClient {

    /**
     * Obtiene un disco por su identificador.
     *
     * @param id identificador del disco
     * @return {@link ResponseEntity} con el {@link DiscoDTO} correspondiente
     */
    @GetMapping("/api/v1/productos/{id}")
    ResponseEntity<DiscoDTO> obtenerDiscoPorId(@PathVariable Long id);
}
