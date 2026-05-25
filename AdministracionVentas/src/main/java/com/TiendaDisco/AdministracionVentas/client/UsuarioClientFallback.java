package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.client.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioClientFallback implements UsuarioClient {

    @Override
    public UsuarioDTO getUserByName(String name) { return null; }

    @Override
    public UsuarioDTO getUserById(Long id) { return null; }
}
