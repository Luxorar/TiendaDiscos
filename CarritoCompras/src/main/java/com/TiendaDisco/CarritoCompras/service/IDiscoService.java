package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Disco;

import java.util.List;

/**
 * Interface que define las operaciones de negocio para discos en el carrito.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface IDiscoService {

    List<Disco> getAllDiscos();

    List<Disco> getListaDiscos(Long user);

    Disco putDisco(Long user, Disco disco);

    Disco getDisco(Long user, Long idDisco);

    String deleteDiscos(Long user, Long idDisco);

    Disco postDisco(Long user, Long idDisco, Disco newDisco);
}
