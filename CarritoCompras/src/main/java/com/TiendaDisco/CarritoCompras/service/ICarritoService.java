package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;


import java.util.List;

public interface ICarritoService {

    List<CarritoDTO> getListaCarrito();

    Carrito postCarrito(Carrito c);

    CarritoDTO getCarrito(String usuario);

    String updateCarrito(Carrito c, String usuario);

    void deleteCarrito(String usuario);
}
