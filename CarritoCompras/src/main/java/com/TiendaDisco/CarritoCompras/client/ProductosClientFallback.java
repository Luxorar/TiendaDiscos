package com.TiendaDisco.CarritoCompras.client;

import com.TiendaDisco.CarritoCompras.client.dto.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductosClientFallback implements ProductosClient {

    @Override
    public ProductoDTO getProductoById(Long id) {
        return null;
    }
}
