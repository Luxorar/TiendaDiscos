package com.TiendaDisco.ManejoStock.client;

import com.TiendaDisco.ManejoStock.client.dto.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductosClientFallback implements ProductosClient {

    @Override
    public ProductoDTO getProductoById(Long id) { return null; }
}
