package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio de logica de negocio para administracion de usuarios.
 * Define las operaciones disponibles para gestionar usuarios y administradores.
 */
public interface IAdminService {

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de {@link UserDTO}
     */
    List<UserDTO> getAllUser();

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param u objeto {@link User} con los datos del usuario
     * @return el usuario creado
     */
    User postUsuario(User u);

    /**
     * Registra un nuevo administrador en el sistema.
     *
     * @param u objeto {@link Admin} con los datos del administrador
     * @return el administrador creado
     */
    Admin postAdmin(Admin u);

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return {@link UserDTO} del usuario encontrado
     */
    UserDTO getUserId(Long id);

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param name nombre del usuario
     * @return {@link UserDTO} del usuario encontrado
     */
    UserDTO getUserName(String name);

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar
     */
    void deleteUserId(Long id);

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id identificador del usuario
     * @param u  objeto {@link User} con los datos actualizados
     * @return el usuario actualizado
     */
    User putUser(Long id, User u);

    /**
     * Actualiza el puntaje de un usuario.
     *
     * @param id      identificador del usuario
     * @param puntaje nuevo puntaje a asignar
     * @return el usuario con el puntaje actualizado
     */
    User putPuntaje(Long id, Integer puntaje);

    /**
     * Obtiene todos los administradores registrados.
     *
     * @return lista de {@link AdminDTO}
     */
    List<AdminDTO> getAllAdmin();

    /**
     * Obtiene un administrador por su identificador.
     *
     * @param id identificador del administrador
     * @return {@link AdminDTO} del administrador encontrado
     */
    AdminDTO getAdminId(Long id);

    /**
     * Obtiene un administrador por su nombre de usuario.
     *
     * @param name nombre del administrador
     * @return {@link AdminDTO} del administrador encontrado
     */
    AdminDTO getAdminName(String name);

    /**
     * Elimina un administrador por su identificador.
     *
     * @param id identificador del administrador a eliminar
     */
    void deleteAdminId(Long id);

    /**
     * Actualiza los datos de un administrador existente.
     *
     * @param id identificador del administrador
     * @param a  objeto {@link Admin} con los datos actualizados
     * @return el administrador actualizado
     */
    Admin putAdmin(Long id, Admin a);

    User addCredito(Long id, BigDecimal monto);

    User putModoOscuro(Long id, Boolean modoOscuro);
}
