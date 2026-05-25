package com.TiendaDisco.RegistroResenas.client;

import com.TiendaDisco.RegistroResenas.client.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioClientFallback implements UsuarioClient {

    @Override
    public UsuarioDTO getUserByName(String name) { return null; }
}
