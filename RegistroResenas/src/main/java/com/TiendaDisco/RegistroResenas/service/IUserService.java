package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.User;

import java.util.List;

/**
 * Contrato que define las opreaciones de negocio disponible para
 * la gestion de los usuarios.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
public interface IUserService {

    /**
     * Registra y guarda un nuevo usuario en la base de datos del sistema.
     * * @param u El objeto {@link User} que contiene la informacion a registrar
     * @return El objeto {@link User} persistido, incluyendo su id generado.
     */
    User postUsuario(User u);

    /**
     * Recupera una lista completamente con todos los usuarios.
     * @return Una lista {@link List} de {@link User}.
     */
    List<UserDTO> getAllUsers();

    /**
     * Busca un usuario en el sistema mediante su identificador unico y devuelve
     * su representacion
     * @param id El identificador del disco
     * @return Un objeto {@link User} con la informacion publica del disco.
     * @throws com.TiendaDisco.RegistroResenas.exception.ManejoErrores si no se encuentra el id indicado
     */
    UserDTO getUserId(Long id);

    /**
     * Actualiza los datos de un usuario existente en la base de datos
     * * @param id El idenetificador unico del usuario que se va a modificar
     * @param u El objeto {@link User} con los nuevos datos que se reemplazaran.
     * @return Un mensaje de confirmacion en formato String.
     * @throws com.TiendaDisco.RegistroResenas.exception.ManejoErrores Si el id a modificar no existe.
     */
    String putUsers(Long id, User u);

    /**
     * Elimina un usuario en base a su id.
     * * @param id el id del usuario a eliminar
     * @return Un mensaje de confirmacion en formato String
     * @throws com.TiendaDisco.RegistroResenas.exception.ManejoErrores Si el id a eliminar no existe.
     */
    String deleteUser(Long id);
}
