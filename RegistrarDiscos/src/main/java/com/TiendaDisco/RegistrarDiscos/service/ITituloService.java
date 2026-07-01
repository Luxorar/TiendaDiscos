package com.TiendaDisco.RegistrarDiscos.service;

import java.util.List;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;

/**
 * Contrato que define las opreaciones de negocio disponible para
 * la gestion de los titulos de los discos.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
public interface ITituloService {

    /**
     * Registra y guarda un nuevo titulo en la base de datos del sistema.
     * * @param t El objeto {@link Titulo} que contiene la informacion a registrar
     * @return El objeto {@link Titulo} persistido, incluyendo su id generado.
     */
    Titulo postTitulo(Titulo t);

    /**
     * Busca un titulo en el sistema mediante su identificador unico y devuelve
     * su representacion
     * @param id El identificador del disco
     * @return Un objeto {@link Titulo} con la informacion publica del disco.
     * @throws com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores si no se encuentra el id indicado
     */
    Titulo getTituloId(Long id);

    /**
     * Actualiza los datos de un titulo existente en la base de datos
     * * @param id El idenetificador unico del disco que se va a modificar
     * @param t El objeto {@link Titulo} con los nuevos datos que se reemplazaran.
     * @return Un mensaje de confirmacion en formato String.
     * @throws com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores Si el id a modificar no existe.
     */
    String putTitulo(Long id, Titulo t);

    /**
     * Elimina un titulo en base a su id.
     * * @param id el id del titulo a eliminar
     * @return Un mensaje de confirmacion en formato String
     * @throws com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores Si el id a eliminar no existe.
     */
    String deleteTitulo(Long id);

    /**
     * Recupera una lista completamente con todos los titulos
     * @return Una lista {@link List} de objetos {@link Titulo}.
     */
    List<Titulo> getAllTitulos();
}
