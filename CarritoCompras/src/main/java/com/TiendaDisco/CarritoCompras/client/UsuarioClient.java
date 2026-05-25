package com.TiendaDisco.CarritoCompras.client;

import com.TiendaDisco.CarritoCompras.client.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin-usuario", url = "http://localhost:8081",
             fallback = UsuarioClientFallback.class)
public interface UsuarioClient {

    @GetMapping("/api/v1/admin/name/{name}")
    UsuarioDTO getUserByName(@PathVariable("name") String name);
}
