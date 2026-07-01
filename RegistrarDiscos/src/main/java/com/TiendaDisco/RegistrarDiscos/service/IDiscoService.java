package com.TiendaDisco.RegistrarDiscos.service;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;

/**
 * Contrato que define las opreaciones de negocio disponible para
 * la gestion del catalogo en la tienda.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
public interface IDiscoService {

    /**
     * Registra y guarda un nuevo disco en la base de datos del sistema.
     * * @param d El objeto {@link Disco} que contiene la informacion a registrar
     * @return El objeto {@link Disco} persistido, incluyendo su id generado.
     */
    Disco postDisco(Disco d);

    /**
     * Busca un disco en el sistema mediante su identificador unico y devuelve
     * su representacion como DTO
     * @param id El identificador del disco
     * @return Un objeto {@link DiscoDTO} con la informacion publica del disco.
     * @throws com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores si no se encuentra el id indicado
     */
    DiscoDTO getDiscoId(Long id);

    /**
     * Actualiza los datos de un disco existente en la base de datos
     * * @param id El idenetificador unico del disco que se va a modificar
     * @param d El objeto {@link Disco} con los nuevos datos que se reemplazaran.
     * @return Un mensaje de confirmacion en formato String.
     * @throws com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores Si el id a modificar no existe.
     */
    String putDisco(Long id, Disco d);

    /**
     * Elimina un disco del catalogo de la tienda en base a su id.
     * * @param id el id del disco a eliminar
     * @return Un mensaje de confirmacion en formato String
     * @throws com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores Si el id a eliminar no existe.
     */
    String deleteDisco(Long id);

    /**
     * Recupera una lista completamente con todos los discos
     * @return Una lista {@link List} de objetos {@link DiscoDTO}.
     */
    List<DiscoDTO> getAllDiscos();
}
