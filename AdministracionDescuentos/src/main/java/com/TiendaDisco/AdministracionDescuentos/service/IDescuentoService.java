package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import java.util.List;

public interface IDescuentoService {

    public List<DescuentoDTO> getAllDescuentos();
    public DescuentoDTO getDescuentoId(Long id);
    public DescuentoDTO getDescuentoNombre(String nombre);
    public Descuento postDescuento(Descuento d);
    public String putDescuento(Long id, Descuento d);
    public String deleteDescuento(Long id);

    public String agregarDisco(String nombreDescuento, Long idDisco);
    public String quitarDisco(String nombreDescuento, Long idDisco);
    public String agregarProducto(String nombreDescuento, Long idProducto);
    public String quitarProducto(String nombreDescuento, Long idProducto);
}
