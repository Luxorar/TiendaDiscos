package com.TiendaDisco.AdministracionDescuentos.mapper;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.client.DiscoClient;
import com.TiendaDisco.AdministracionDescuentos.client.ProductoClient;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    private DiscoClient discoClient;

    @Autowired
    private ProductoClient productoClient;

    public DescuentoDTO toDTO(Descuento descuento){
        if(descuento == null) return null;

        return DescuentoDTO.builder()
                .id(descuento.getId())
                .nombre(descuento.getNombre())
                .estado(descuento.getEstado())
                .descuento(descuento.getDescuento())
                .discosAgregados(resolverNombresDiscos(descuento.getDiscoIds()))
                .productosAgregados(resolverNombresProductos(descuento.getProductoIds()))
                .build();
    }

    private List<String> resolverNombresDiscos(List<Long> ids) {
        if (ids == null) return Collections.emptyList();
        return ids.stream()
                .map(id -> {
                    try {
                        var response = discoClient.obtenerDiscoPorId(id);
                        return response.getBody() != null ? response.getBody().getNombreDisco() : "Disco no disponible";
                    } catch (Exception e) {
                        return "Error al obtener disco ID: " + id;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<String> resolverNombresProductos(List<Long> ids) {
        if (ids == null) return Collections.emptyList();
        return ids.stream()
                .map(id -> {
                    try {
                        var response = productoClient.obtenerProductoPorId(id);
                        return response.getBody() != null ? response.getBody().getNombreProducto() : "Producto no disponible";
                    } catch (Exception e) {
                        return "Error al obtener producto ID: " + id;
                    }
                })
                .collect(Collectors.toList());
    }
}
