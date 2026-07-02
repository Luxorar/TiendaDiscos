package com.TiendaDisco.AdministracionVentas.service;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface que define las operaciones de negocio para la gestion de ventas.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface IVentaService {

    /**
     * Registra una nueva venta.
     *
     * @param v entidad {@link Venta} con los datos a persistir
     * @return {@link VentaDTO} con la venta registrada
     */
    VentaDTO postVenta(Venta v);

    /**
     * Elimina una venta por su identificador.
     *
     * @param id identificador de la venta a eliminar
     */
    void delVenta(Long id);

    /**
     * Obtiene todas las ventas registradas.
     *
     * @return lista de {@link VentaDTO}
     */
    List<VentaDTO> getAllVentas();

    /**
     * Obtiene ventas aplicando filtros opcionales por fecha y usuario.
     *
     * @param fechaInicio fecha minima (opcional)
     * @param fechaFin    fecha maxima (opcional)
     * @param usuarioId   identificador del usuario (opcional)
     * @return lista de {@link VentaDTO} filtrada
     */
    List<VentaDTO> getAllVentas(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId);

    /**
     * Obtiene una venta por su identificador.
     *
     * @param id identificador de la venta
     * @return {@link VentaDTO} con los datos de la venta
     */
    VentaDTO getVentaId(Long id);

    /**
     * Obtiene las ventas de un usuario especifico.
     *
     * @param u identificador del usuario
     * @return lista de {@link VentaDTO} del usuario
     */
    List<VentaDTO> getVentaUser(Long u);
}
