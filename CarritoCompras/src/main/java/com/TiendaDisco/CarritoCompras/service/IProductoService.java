package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;

import java.util.ArrayList;
import java.util.List;

public interface IProductoService {

    List<Producto> getAllProductos();

    Producto putProducto(Long user, Producto producto);

    ArrayList<Producto> getListaProducto(Long user, Producto producto);

    Producto getProducto(Long user, Long idProducto);

    String deleteProducto(Long user, Long idProducto);

    Producto postProducto(Long user, Long idProducto, Producto newProducto);
}
