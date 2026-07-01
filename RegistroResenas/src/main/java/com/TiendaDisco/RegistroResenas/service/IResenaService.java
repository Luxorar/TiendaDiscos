package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.model.Resena;

import java.util.List;

/**
 * Contrato que define las opreaciones de negocio disponible para
 * la gestion de las resenas de los discos.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
public interface IResenaService {

    /**
     * Registra y guarda una nueva resena en la base de datos del sistema.
     * * @param r El objeto {@link Resena} que contiene la informacion a registrar
     * @return El objeto {@link Resena} persistido, incluyendo su id generado.
     */
    Resena postResena(Resena r);

    /**
     * Recupera una lista completamente con todas las resenas
     * @return Una lista {@link List} de objetos {@link Resena}.
     */
    List<ResenaDTO> getAllResenas();

    /**
     * Busca una resena en el sistema mediante su identificador unico y devuelve
     * su representacion
     * @param id El identificador de la resena
     * @return Un objeto {@link Resena} con la informacion publica de la Resena.
     * @throws com.TiendaDisco.RegistroResenas.exception.ManejoErrores si no se encuentra el id indicado
     */
    ResenaDTO getResenaId(Long id);

    /**
     * Elimina una resena en base a su id.
     * * @param id el id de la resena a eliminar
     * @return Un mensaje de confirmacion en formato String
     * @throws com.TiendaDisco.RegistroResenas.exception.ManejoErrores Si el id a eliminar no existe.
     */
    String deleteResena(Long id);
}
