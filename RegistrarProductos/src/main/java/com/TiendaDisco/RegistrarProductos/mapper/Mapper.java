package com.TiendaDisco.RegistrarProductos.mapper;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.model.Producto;

import java.util.List;

public class Mapper {

    public static ProductoDTO toDTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombreProducto(producto.getNombreProducto())
                .marca(producto.getMarca())
                .precio(producto.getPrecio())
                .build();
    }

    public static List<ProductoDTO> toDTOList(List<Producto> productos) {
        return productos.stream()
                .map(Mapper::toDTO)
                .toList();
    }
}
