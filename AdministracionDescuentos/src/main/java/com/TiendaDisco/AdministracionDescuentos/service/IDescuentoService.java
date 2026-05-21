package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import java.util.List;

public interface IDescuentoService {
    List<Descuento> getAllDescuentos();

    Descuento getDescuentoId(Long id);

    Descuento getDescuentoNombre(String nombre);

    Descuento postDescuento(Descuento d);

    String putDescuento(Long id, Descuento d);

    String deleteDescuento(Long id);

    String agregarDisco(String nombreDescuento, Long idDisco);
}