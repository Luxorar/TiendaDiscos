package com.TiendaDisco.AdministracionDescuentos.client;

import com.TiendaDisco.AdministracionDescuentos.client.dto.DiscoDTO;
import org.springframework.stereotype.Component;

@Component
public class DiscosClientFallback implements DiscosClient {

    @Override
    public DiscoDTO getDiscoById(Long id) { return null; }
}
