package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.mapper.Mapper;
import com.TiendaDisco.RegistroResenas.model.User;
import com.TiendaDisco.RegistroResenas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository repo;

    //==================REGISTRA UN USUARIO================================
    public User postUsuario(User u){
        return repo.save(u);
    }

    //==================OBTIENE TODOS LOS USUARIOS================================
    public List<UserDTO> getAllUsers(){
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    //==================OBTIENE USUARIO POR ID================================
    public UserDTO getUserId(Long id){
        User user = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
        return Mapper.toDTO(user);
    }

    //==================MODIFICA UN USUARIO================================
    public String putUsers(Long id, User u){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        usuario.setUserName(u.getUserName());
        usuario.setGmail(u.getGmail());
        return "Usuario modificado";
    }

    //==================ELIMINA UN USUARIO================================
    public String deleteUser(Long id){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(usuario);
        return "Usuario elimiando";
    }
}
