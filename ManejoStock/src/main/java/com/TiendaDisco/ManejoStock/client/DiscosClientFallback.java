package com.TiendaDisco.ManejoStock.client;

import com.TiendaDisco.ManejoStock.client.dto.DiscoDTO;
import org.springframework.stereotype.Component;

@Component
public class DiscosClientFallback implements DiscosClient {

    @Override
    public DiscoDTO getDiscoById(Long id) { return null; }
}
