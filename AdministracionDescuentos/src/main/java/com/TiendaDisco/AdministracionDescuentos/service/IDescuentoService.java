package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import java.util.List;

/**
 * Interface que define las operaciones de negocio para descuentos.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface IDescuentoService {

    List<DescuentoDTO> getAllDescuentos();
    DescuentoDTO getDescuentoId(Long id);
    DescuentoDTO getDescuentoNombre(String nombre);
    Descuento postDescuento(Descuento d);
    String putDescuento(Long id, Descuento d);
    String deleteDescuento(Long id);

    String agregarDisco(String nombreDescuento, Long idDisco);
    String quitarDisco(String nombreDescuento, Long idDisco);
    String agregarProducto(String nombreDescuento, Long idProducto);
    String quitarProducto(String nombreDescuento, Long idProducto);
}
