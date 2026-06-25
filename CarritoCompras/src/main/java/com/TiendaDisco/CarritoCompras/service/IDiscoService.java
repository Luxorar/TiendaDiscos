package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Disco;

import java.util.List;

public interface IDiscoService {

    List<Disco> getAllDiscos();

    List<Disco> getListaDiscos(Long user);

    Disco putDisco(Long user, Disco disco);

    Disco getDisco(Long user, Long idDisco);

    String deleteDiscos(Long user, Long idDisco);

    Disco postDisco(Long user, Long idDisco, Disco newDisco);
}
