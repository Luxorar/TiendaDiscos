package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.User;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    List<UserDTO> getAllUser();

    User postUsuario(User u);

    UserDTO getUserId(Long id);

    UserDTO getUserName(String name);

    void deleteUserId(Long id);

    User putUser(Long id, User u);

    User putPuntaje(Long id, Integer puntaje);
}
