package com.TiendaDisco.AdministracionVentas.service;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.exception.ManejoErrores;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import com.TiendaDisco.AdministracionVentas.repository.VentaRepository;
import com.TiendaDisco.AdministracionVentas.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementacion del servicio de ventas.
 * Contiene la logica de negocio para gestionar las ventas del sistema.
 */
@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private Mapper mapper;

    /**
     * Registra una nueva venta y la convierte a DTO.
     *
     * @param v objeto Venta a persistir
     * @return VentaDTO de la venta registrada
     */
    @Override
    @Transactional
    public VentaDTO postVenta(Venta v) {
        ventaRepository.save(v);
        return mapper.toDTO(v);
    }

    /**
     * Elimina una venta si existe, lanzando excepcion en caso contrario.
     *
     * @param id identificador de la venta
     */
    @Override
    @Transactional
    public void delVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));

        ventaRepository.delete(venta);
    }

    /**
     * Obtiene todas las ventas registradas.
     *
     * @return lista de VentaDTO
     */
    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    /**
     * Obtiene ventas filtradas por rango de fechas y/o usuario.
     *
     * @param fechaInicio fecha de inicio del filtro
     * @param fechaFin    fecha de fin del filtro
     * @param usuarioId   identificador del usuario
     * @return lista de VentaDTO filtrada
     */
    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> getAllVentas(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId) {
        return ventaRepository.findWithFilters(fechaInicio, fechaFin, usuarioId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    /**
     * Busca una venta por id, lanzando excepcion si no existe.
     *
     * @param id identificador de la venta
     * @return VentaDTO de la venta encontrada
     */
    @Override
    @Transactional(readOnly = true)
    public VentaDTO getVentaId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));

        return mapper.toDTO(venta);
    }

    /**
     * Obtiene todas las ventas de un usuario.
     *
     * @param u identificador del usuario
     * @return lista de VentaDTO
     */
    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> getVentaUser(Long u) {
        return ventaRepository.findByUsuario(u)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    /**
     * Obtiene los productos asociados a una venta por su id.
     *
     * @param id identificador de la venta
     * @return lista de productos de la venta
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductoReciboId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));
        return venta.getProductosComprados();
    }
}
