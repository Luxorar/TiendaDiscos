package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.model.Producto;

public interface IProductoService {
    Producto postProducto(Producto p);

    Producto getProductoId(Long id);

    String putProducto(Long id, Producto p);

    String deleteProducto(Long id);
}
