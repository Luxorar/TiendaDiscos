package com.TiendaDisco.AdministracionVentas.service;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    VentaDTO postVenta(Venta v);

    void delVenta(Long id);

    List<VentaDTO> getAllVentas();

    List<VentaDTO> getAllVentas(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId);

    VentaDTO getVentaId(Long id);

    List<VentaDTO> getVentaUser(Long u);

}
