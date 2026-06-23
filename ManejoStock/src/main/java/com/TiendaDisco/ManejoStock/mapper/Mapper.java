package com.TiendaDisco.ManejoStock.mapper;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.model.infoStock;

public class Mapper {

    public static InfoStockDTO toDTO(infoStock stock) {
        if (stock == null) return null;

        return InfoStockDTO.builder()
                .id(stock.getId())
                .nombreProducto(stock.getNombreProducto())
                .nombreSede(stock.getSede() != null ? stock.getSede().getNombreSede() : null)
                .stockActual(stock.getStockActual())
                .build();
    }
}
