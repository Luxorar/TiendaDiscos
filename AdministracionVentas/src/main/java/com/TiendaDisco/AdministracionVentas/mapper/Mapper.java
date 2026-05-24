package com.TiendaDisco.AdministracionVentas.mapper;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

        public static VentaDTO toDTO(Venta c){
            if (c== null) return null;

            List<String> listaProductosString = new ArrayList<>();
            int subTotalCalculado = 0;

            for(Producto i: c.getProductosComprados()){
                listaProductosString.add(i.getNombre() + " , "+ String.valueOf(i.getPrecio()));
            }

            for(Producto i: c.getProductosComprados()){
                subTotalCalculado = subTotalCalculado + i.getPrecio();
            }


            return VentaDTO.builder()
                    .id(c.getId())
                    .productosComprados(listaProductosString)
                    .fechaVenta(c.getFechaVenta())
                    .usuario(c.getUsuario().getGmail())
                    .puntosUsados(c.getPuntosUsados())
                    .puntosGanados(c.getPuntosGanados())
                    .subtotal(subTotalCalculado)
                    .descuento(c.getDescuento())
                    .totalPagar(Math.round(subTotalCalculado* c.getDescuento()))
                    .build();

        }

}
