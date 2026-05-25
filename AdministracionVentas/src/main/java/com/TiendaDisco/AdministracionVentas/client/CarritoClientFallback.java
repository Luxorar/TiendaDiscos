package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.client.dto.CarritoDTO;
import org.springframework.stereotype.Component;

@Component
public class CarritoClientFallback implements CarritoClient {

    @Override
    public CarritoDTO getCarritoByUser(String username) { return null; }
}
