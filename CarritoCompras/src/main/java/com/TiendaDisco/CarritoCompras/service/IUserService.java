package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User postUser(User user);

    User getUserId(Long id);

    User deleteUser(Long id);

    User updateUser(User user, Long id);
}
