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

/**
 * Clase utilitaria para mapear entidades de descuento a DTOs.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Component
public class Mapper {

    @Autowired
    private DiscoClient discoClient;

    @Autowired
    private ProductoClient productoClient;

    /**
     * Convierte una entidad {@link Descuento} a su DTO, resolviendo
     * los nombres de discos y productos asociados mediante Feign clients.
     *
     * @param descuento entidad Descuento a convertir, puede ser {@code null}
     * @return {@link DescuentoDTO} con los datos mapeados
     */
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

    /**
     * Resuelve los nombres de los discos a partir de sus IDs.
     *
     * @param ids lista de identificadores de discos
     * @return lista de nombres de discos
     */
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

    /**
     * Resuelve los nombres de los productos a partir de sus IDs.
     *
     * @param ids lista de identificadores de productos
     * @return lista de nombres de productos
     */
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
