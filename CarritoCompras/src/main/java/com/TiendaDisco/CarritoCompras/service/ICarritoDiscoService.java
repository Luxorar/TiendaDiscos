package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDiscoDTO;

import java.util.List;

public interface ICarritoDiscoService {
    List<CarritoDiscoDTO> getListaDiscos(Long user);
    CarritoDiscoDTO addDisco(Long user, Long discoId);
    CarritoDiscoDTO updateQty(Long user, Long discoId, int qty);
    String deleteDisco(Long user, Long discoId);
    void clearDiscos(Long user);
}
