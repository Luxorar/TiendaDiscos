package com.TiendaDisco.AdministracionDescuentos.client;

import com.TiendaDisco.AdministracionDescuentos.client.dto.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductosClientFallback implements ProductosClient {

    @Override
    public ProductoDTO getProductoById(Long id) { return null; }
}
