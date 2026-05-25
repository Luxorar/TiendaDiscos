package com.TiendaDisco.ManejoStock.client;

import com.TiendaDisco.ManejoStock.client.dto.SedeDTO;
import org.springframework.stereotype.Component;

@Component
public class SedeClientFallback implements SedeClient {

    @Override
    public SedeDTO getSedeById(Long id) { return null; }
}
