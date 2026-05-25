package com.TiendaDisco.AdministracionEnvios.client;

import com.TiendaDisco.AdministracionEnvios.client.dto.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin-ventas", url = "http://localhost:8091",
             fallback = VentasClientFallback.class)
public interface VentasClient {

    @GetMapping("api/v1/ventas/id/{id}")
    VentaDTO getVentaById(@PathVariable("id") Long id);
}
