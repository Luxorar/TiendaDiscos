package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.client.dto.DescuentoDTO;
import org.springframework.stereotype.Component;

@Component
public class DescuentosClientFallback implements DescuentosClient {

    @Override
    public DescuentoDTO getDescuentoById(Long id) { return null; }
}
