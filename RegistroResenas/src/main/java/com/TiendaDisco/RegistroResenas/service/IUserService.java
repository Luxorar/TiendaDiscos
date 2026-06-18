package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.model.User;

import java.util.List;

public interface IUserService {
    User postUsuario(User u);

    List<User> getAllUsers();

    User getUserId(Long id);

    String putUsers(Long id, User u);

    String deleteUser(Long id);
}
