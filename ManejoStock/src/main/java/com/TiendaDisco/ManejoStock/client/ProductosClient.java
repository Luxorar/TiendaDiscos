package com.TiendaDisco.ManejoStock.client;

import com.TiendaDisco.ManejoStock.client.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "registrar-productos", url = "http://localhost:8083",
             fallback = ProductosClientFallback.class)
public interface ProductosClient {

    @GetMapping("/api/v1/productos/{id}")
    ProductoDTO getProductoById(@PathVariable("id") Long id);
}
