package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.User;

import java.util.List;

public interface IUserService {
    User postUsuario(User u);

    List<UserDTO> getAllUsers();

    UserDTO getUserId(Long id);

    String putUsers(Long id, User u);

    String deleteUser(Long id);
}
