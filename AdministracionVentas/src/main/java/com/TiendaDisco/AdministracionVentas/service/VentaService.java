package com.TiendaDisco.AdministracionVentas.service;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.exception.ManejoErrores;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import com.TiendaDisco.AdministracionVentas.repository.VentaRepository;
import com.TiendaDisco.AdministracionVentas.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public VentaDTO postVenta(Venta v) {
        ventaRepository.save(v);
        return Mapper.toDTO(v);
    }

    @Override
    public void delVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));

        ventaRepository.delete(venta);
    }

    @Override
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public VentaDTO getVentaId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Venta no encontrada"));

        return Mapper.toDTO(venta);
    }

    @Override
    public List<VentaDTO> getVentaUser(String u) {
        return ventaRepository.findByUsuarioUserName(u)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<Producto> getProductoReciboId(Long id) {
        Venta v = ventaRepository
                .findById(id).orElseThrow(() -> new ManejoErrores("Venta no enocntrada"));

        return v.getProductosComprados();
    }
}