package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.model.infoStock;
import java.util.List;

public interface IInfoStockService {
    infoStock postInfoStock(infoStock stock);

    List<infoStock> getSedeInfo(String nombreSede);

    infoStock getProductoInfo(String nombreProducto);

    infoStock getInfoID(Long id);

    String putNombreProducto(Long id, String nuevoNombre);

    String putStock(Long id, int nuevoStock);

    String putSede(Long id, String nombreSede);

    String deleteInfo(Long id);
}