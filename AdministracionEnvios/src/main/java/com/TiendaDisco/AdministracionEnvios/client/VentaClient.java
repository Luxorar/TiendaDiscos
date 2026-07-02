package com.TiendaDisco.AdministracionEnvios.client;

import com.TiendaDisco.AdministracionEnvios.DTO.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para comunicacion con el microservicio de Ventas.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@FeignClient(name="ventaClient", url="http://administracion-ventas:8091")
public interface VentaClient {

    /**
     * Obtiene una venta por su identificador.
     *
     * @param id identificador de la venta
     * @return {@link ResponseEntity} con el {@link VentaDTO}
     */
    @GetMapping("api/v1/ventas/id/{id}")
    public ResponseEntity<VentaDTO> getVentaId(@PathVariable Long id);
}
