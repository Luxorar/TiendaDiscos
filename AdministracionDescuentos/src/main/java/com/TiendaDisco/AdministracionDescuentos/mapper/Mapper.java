package com.TiendaDisco.AdministracionDescuentos.mapper;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Disco;
import com.TiendaDisco.AdministracionDescuentos.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static DescuentoDTO toDTO(Descuento descuento){
        if(descuento == null) return null;

        List<String> discos = new ArrayList<>();
        List<String> productos = new ArrayList<>();

        for(Disco d: descuento.getDiscosAgregados()){
            discos.add(d.getArtista()+" - "+d.getNombreDisco());
        }

        for (Producto p: descuento.getProductosAgregados()){
            productos.add(p.getNombreProducto()+" - "+p.getMarca());
        }

        return  DescuentoDTO.builder()
                .id(descuento.getId())
                .nombre(descuento.getNombre())
                .estado(descuento.getEstado())
                .discosAgregados(discos)
                .productosAgregados(productos)
                .build();
    }
}
