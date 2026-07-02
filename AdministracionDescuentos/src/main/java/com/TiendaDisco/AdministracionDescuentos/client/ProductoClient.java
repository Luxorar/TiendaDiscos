package com.TiendaDisco.AdministracionDescuentos.client;

import com.TiendaDisco.AdministracionDescuentos.DTO.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para comunicacion con el microservicio de Productos.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@FeignClient(name="ProductoClient", url= "http://registrar-productos:8083")
public interface ProductoClient {

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id identificador del producto
     * @return {@link ResponseEntity} con el {@link ProductoDTO}
     */
    @GetMapping("/api/v1/productos/{id}")
    ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id);
}
