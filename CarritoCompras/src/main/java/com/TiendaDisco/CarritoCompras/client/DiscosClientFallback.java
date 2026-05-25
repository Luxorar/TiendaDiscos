package com.TiendaDisco.CarritoCompras.client;

import com.TiendaDisco.CarritoCompras.client.dto.DiscoDTO;
import org.springframework.stereotype.Component;

@Component
public class DiscosClientFallback implements DiscosClient {

    @Override
    public DiscoDTO getDiscoById(Long id) {
        return null;
    }
}
