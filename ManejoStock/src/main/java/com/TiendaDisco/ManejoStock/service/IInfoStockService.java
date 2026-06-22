package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.model.infoStock;
import java.util.List;

public interface IInfoStockService {
    infoStock postInfoStock(infoStock stock);

    List<InfoStockDTO> getSedeInfo(String nombreSede);

    InfoStockDTO getProductoInfo(String nombreProducto);

    InfoStockDTO getInfoID(Long id);

    String putNombreProducto(Long id, String nuevoNombre);

    String putStock(Long id, int nuevoStock);

    String putSede(Long id, String nombreSede);

    String deleteInfo(Long id);

    List<InfoStockDTO> getAllInfoStock();
}