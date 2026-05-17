package com.TiendaDisco.RegistrarProductos.service;

import com.TiendaDisco.RegistrarProductos.model.Producto;
import java.util.List;

public interface IProductoService {
    Producto postProducto(Producto p);
    List<Producto> getListaProducto();
    Producto getProductoID(Long id);
    List<Producto> getProductoNombre(String nombre);
    String deleteProducto(Long id);
    List<Producto> getProductoMarca(String marca);
}