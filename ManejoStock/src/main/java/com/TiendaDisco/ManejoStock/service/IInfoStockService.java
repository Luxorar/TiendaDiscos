package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.model.infoStock;
import java.util.List;

public interface IInfoStockService {
    infoStock postInfoStock(infoStock stock);

    List<InfoStockDTO> getSedeInfo(String nombreSede);

    InfoStockDTO getProductoInfo(String nombreProducto);

    InfoStockDTO getInfoID(Long id);

    String putStock(Long id, int nuevoStock);

    String deleteInfo(Long id);

    List<InfoStockDTO> getAllInfoStock();

    int getStockTotalByDiscoId(Long discoId);
}