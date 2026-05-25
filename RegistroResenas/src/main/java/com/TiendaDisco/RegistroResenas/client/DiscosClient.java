package com.TiendaDisco.RegistroResenas.client;

import com.TiendaDisco.RegistroResenas.client.dto.DiscoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "registrar-discos", url = "http://localhost:8086",
             fallback = DiscosClientFallback.class)
public interface DiscosClient {

    @GetMapping("/api/v1/productos/{id}")
    DiscoDTO getDiscoById(@PathVariable("id") Long id);
}
