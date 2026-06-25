package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.ProductoDTO;
import com.TiendaDisco.RegistrarSede.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto postProducto(Producto p);
    List<ProductoDTO> getAllProductos();
    ProductoDTO getProductoId(Long id);
    String putProducto(Long id, Producto p);
    String deleteProducto(Long id);
}
