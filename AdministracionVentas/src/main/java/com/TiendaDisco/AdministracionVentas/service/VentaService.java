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

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public VentaDTO postVenta(Venta v) {
        ventaRepository.save(v);
        return mapper.toDTO(v);
    }

    @Override
    @Transactional
    public void delVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));

        ventaRepository.delete(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> getAllVentas(LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId) {
        return ventaRepository.findWithFilters(fechaInicio, fechaFin, usuarioId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VentaDTO getVentaId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));

        return mapper.toDTO(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> getVentaUser(Long u) {
        return ventaRepository.findByUsuario(u)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<Producto> getProductoReciboId(Long id) {
        Venta v = ventaRepository
                .findById(id).orElseThrow(() -> new ManejoErrores("Venta no enocntrada"));

        return v.getProductosComprados();
    }
}
