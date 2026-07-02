package com.TiendaDisco.AdministracionVentas.service;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio de logica de negocio para ventas.
 * Define las operaciones disponibles para gestionar ventas.
 */
public interface IVentaService {

    /**
     * Registra una nueva venta en el sistema.
     *
     * @param v objeto {@link Venta} con los datos de la venta
     * @return la venta registrada como {@link VentaDTO}
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
     * Obtiene ventas filtradas por rango de fechas y/o usuario.
     *
     * @param fechaInicio fecha de inicio del filtro
     * @param fechaFin    fecha de fin del filtro
     * @param usuarioId   identificador del usuario
     * @return lista de {@link VentaDTO} filtrada
     */
    List<VentaDTO> getAllVentas(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId);

    /**
     * Obtiene una venta por su identificador.
     *
     * @param id identificador de la venta
     * @return {@link VentaDTO} de la venta encontrada
     */
    VentaDTO getVentaId(Long id);

    /**
     * Obtiene todas las ventas de un usuario.
     *
     * @param u identificador del usuario
     * @return lista de {@link VentaDTO}
     */
    List<VentaDTO> getVentaUser(Long u);

    /**
     * Obtiene los productos asociados a una venta.
     *
     * @param id identificador de la venta
     * @return lista de productos de la venta
     */
    List<Producto> getProductoReciboId(Long id);
}
