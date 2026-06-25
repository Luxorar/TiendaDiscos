package com.TiendaDisco.AdministracionEnvios.client;

import com.TiendaDisco.AdministracionEnvios.DTO.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ventaClient", url="http://administracion-ventas:8091")
public interface VentaClient {
    @GetMapping("api/v1/ventas/id/{id}")
    public ResponseEntity<VentaDTO> getVentaId(@PathVariable Long id);

}
