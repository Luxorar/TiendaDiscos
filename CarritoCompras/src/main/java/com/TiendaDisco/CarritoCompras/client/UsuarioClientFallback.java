package com.TiendaDisco.CarritoCompras.client;

import com.TiendaDisco.CarritoCompras.client.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioClientFallback implements UsuarioClient {

    @Override
    public UsuarioDTO getUserByName(String name) {
        return null;
    }
}
