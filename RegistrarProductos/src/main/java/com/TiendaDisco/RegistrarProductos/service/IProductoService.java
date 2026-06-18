package com.TiendaDisco.RegistrarProductos.service;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto postProducto(Producto p);
    List<ProductoDTO> getAllProductos();
    ProductoDTO getProductoID(Long id);
    List<ProductoDTO> getProductoNombre(String nombre);
    String deleteProducto(Long id);
    List<ProductoDTO> getProductoMarca(String marca);
}
