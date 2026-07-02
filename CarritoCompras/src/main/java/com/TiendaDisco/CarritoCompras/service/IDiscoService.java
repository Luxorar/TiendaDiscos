package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Disco;

import java.util.List;

/**
 * Servicio de logica de negocio para discos del carrito.
 * Define las operaciones disponibles para gestionar discos en el carrito.
 */
public interface IDiscoService {

    /**
     * Obtiene todos los discos registrados.
     *
     * @return lista de discos
     */
    List<Disco> getAllDiscos();

    /**
     * Obtiene la lista de discos del carrito de un usuario.
     *
     * @param user identificador del usuario
     * @return lista de discos del carrito
     */
    List<Disco> getListaDiscos(Long user);

    /**
     * Modifica un disco existente en el carrito de un usuario.
     *
     * @param user  identificador del usuario
     * @param disco objeto con los datos actualizados
     * @return el disco modificado
     */
    Disco putDisco(Long user, Disco disco);

    /**
     * Obtiene un disco especifico del carrito de un usuario.
     *
     * @param user    identificador del usuario
     * @param idDisco identificador del disco
     * @return el disco solicitado
     */
    Disco getDisco(Long user, Long idDisco);

    /**
     * Elimina un disco del carrito de un usuario.
     *
     * @param user    identificador del usuario
     * @param idDisco identificador del disco
     * @return mensaje de confirmacion
     */
    String deleteDiscos(Long user, Long idDisco);

    /**
     * Agrega un nuevo disco al carrito de un usuario.
     *
     * @param user     identificador del usuario
     * @param idDisco  identificador del disco
     * @param newDisco datos del disco a agregar
     * @return el disco agregado
     */
    Disco postDisco(Long user, Long idDisco, Disco newDisco);
}
