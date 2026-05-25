package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.client.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito-compras", url = "http://localhost:8088",
             fallback = CarritoClientFallback.class)
public interface CarritoClient {

    @GetMapping("/api/v1/carrito/{username}")
    CarritoDTO getCarritoByUser(@PathVariable("username") String username);
}
