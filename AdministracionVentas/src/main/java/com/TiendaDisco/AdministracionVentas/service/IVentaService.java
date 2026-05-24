package com.TiendaDisco.AdministracionVentas.service;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;

import java.util.List;

public interface IVentaService {
    VentaDTO postVenta(Venta v);

    void delVenta(Long id);

    List<VentaDTO> getAllVentas();

    VentaDTO getVentaId(Long id);

    List<VentaDTO> getVentaUser(String u);

    List<Producto> getProductoReciboId(Long id);

}
