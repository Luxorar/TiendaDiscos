package com.TiendaDisco.RegistroResenas.client;

import com.TiendaDisco.RegistroResenas.client.dto.DiscoDTO;
import org.springframework.stereotype.Component;

@Component
public class DiscosClientFallback implements DiscosClient {

    @Override
    public DiscoDTO getDiscoById(Long id) { return null; }
}
