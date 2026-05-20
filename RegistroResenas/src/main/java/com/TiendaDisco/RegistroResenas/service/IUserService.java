package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.model.User;

public interface IUserService {
    User postUsuario(User u);

    User getUserId(Long id);

    String putUsers(Long id, User u);

    String deleteUser(Long id);
}
