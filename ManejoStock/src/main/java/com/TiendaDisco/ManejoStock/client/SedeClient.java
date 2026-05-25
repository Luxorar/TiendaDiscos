package com.TiendaDisco.ManejoStock.client;

import com.TiendaDisco.ManejoStock.client.dto.SedeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "registrar-sede", url = "http://localhost:8084",
             fallback = SedeClientFallback.class)
public interface SedeClient {

    @GetMapping("api/v1/Sede/{id}")
    SedeDTO getSedeById(@PathVariable("id") Long id);
}
