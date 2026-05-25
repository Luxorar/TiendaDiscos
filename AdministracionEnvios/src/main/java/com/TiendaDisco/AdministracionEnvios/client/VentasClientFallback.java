package com.TiendaDisco.AdministracionEnvios.client;

import com.TiendaDisco.AdministracionEnvios.client.dto.VentaDTO;
import org.springframework.stereotype.Component;

@Component
public class VentasClientFallback implements VentasClient {

    @Override
    public VentaDTO getVentaById(Long id) { return null; }
}
