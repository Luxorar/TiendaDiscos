package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.model.User;
import com.TiendaDisco.RegistroResenas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository repo;

    public User postUsuario(User u){
        return repo.save(u);
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User getUserId(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
    }

    public String putUsers(Long id, User u){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        usuario.setUserName(u.getUserName());
        usuario.setGmail(u.getGmail());
        return "Usuario modificado";
    }

    public String deleteUser(Long id){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(usuario);
        return "Usuario elimiando";
    }
}
