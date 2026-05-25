package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.User;
import com.TiendaDisco.CarritoCompras.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User postUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado con id: " + id));
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado con id: " + id));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User updateUser(User user, Long id) {
        User existente = userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado con id: " + id));

        if (user.getUserName() != null) existente.setUserName(user.getUserName());
        if (user.getGmail() != null) existente.setGmail(user.getGmail());
        if (user.getPassword() != null) existente.setPassword(user.getPassword());

        return userRepository.save(existente);
    }
}
