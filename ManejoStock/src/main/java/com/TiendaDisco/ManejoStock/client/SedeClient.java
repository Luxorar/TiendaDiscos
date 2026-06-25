package com.TiendaDisco.ManejoStock.client;

import com.TiendaDisco.ManejoStock.DTO.SedeDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="SedeClient", url="http://registrar-sede:8084")
public interface SedeClient {

    @GetMapping("/api/v1/Sede/{id}")
    public SedeDTO getSedeId(@Valid @PathVariable Long id);
}
