package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ProductoClient", url= "http://registrar-productos:8083")
public interface ProductoClient {

    @GetMapping("/api/v1/productos/{id}")
    ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id);

}
