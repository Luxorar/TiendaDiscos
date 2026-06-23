package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Disco;

import java.util.List;

public interface IDiscoService {

    List<Disco> getAllDiscos();

    List<Disco> getListaDiscos(String user);

    Disco putDisco(String user, Disco disco);

    Disco getDisco(String user, Long idDisco);

    String deleteDiscos(String user, Long idDisco);

    Disco postDisco(String user, Long idDisco, Disco newDisco);
}
