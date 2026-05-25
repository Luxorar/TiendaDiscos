package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.client.dto.DescuentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin-descuentos", url = "http://localhost:8087",
             fallback = DescuentosClientFallback.class)
public interface DescuentosClient {

    @GetMapping("/api/v1/descuentos/{id}")
    DescuentoDTO getDescuentoById(@PathVariable("id") Long id);
}
