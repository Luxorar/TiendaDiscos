package com.TiendaDisco.CarritoCompras.mapper;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;

import java.util.List;

public class Mapper {

    public static CarritoDTO toDTO(Carrito c){
        if (c== null) return null;
        int suma = 0;

        List<Disco> listadiscos = c.getDiscosAgregados();
        List<Producto> listaproductos = c.getProductosAgregados();

        for(Disco disc: listadiscos){
            suma = suma + disc.getPrecio();
        }
        for(Producto pro: listaproductos){
            suma = suma + pro.getPrecio();
        }

        return CarritoDTO.builder()
                .id(c.getId())
                .user(c.getUser().getUserName())
                .precioSolid(suma)
                .productosAgregados(c.getProductosAgregados())
                .discosAgregados(c.getDiscosAgregados())
                .descuento(c.getDescuento())
                .precioLiquido((int) Math.round(suma))
                .build();

    }
}
