package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;

import java.util.ArrayList;

public interface IProductoService {

    Producto putProducto(String user, Producto producto);

    ArrayList<Producto> getListaProducto(String user, Producto producto);

    Producto getProducto(String user, Long idProducto);

    String deleteProducto(String user, Long idProducto);

    Producto postProducto(String user, Long idProducto, Producto newProducto);
}
