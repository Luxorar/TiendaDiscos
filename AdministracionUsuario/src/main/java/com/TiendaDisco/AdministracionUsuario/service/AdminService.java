package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.exception.ManejoErrores;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.mapper.Mapper;
import com.TiendaDisco.AdministracionUsuario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public User postUsuario(User u) {
        u.setFechaRegistro(LocalDate.now());
        u.setCuentaActiva(true);

        return userRepository.save(u) ;
    }

    @Override
    public UserDTO getUserId(Long id) {
        User u =userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("id no encontrado"));

        return Mapper.toDTO(u);
    }

    @Override
    public UserDTO getUserName(String name) {
        User u = userRepository.findByUserName(name)
                .orElseThrow(()-> new ManejoErrores( "No se encontró un usuario con ese nombre"));
        if(u.getCuentaActiva()==true) {
            return Mapper.toDTO(u);
        }else {
            return null;
        }
    }

    @Override
    public void deleteUserId(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(()-> new ManejoErrores( "No se encontró un usuario con ese nombre"));

        userRepository.delete(u);
    }

    @Override
    public User putUser(Long id, User u) {
        User usuario = userRepository.findById(id)
                .orElseThrow(()-> new ManejoErrores( "No se encontró un usuario con ese id"));

        if(u.getUserName()!=null)usuario.setUserName(u.getUserName());
        if(u.getPuntos()!=null)usuario.setPuntos(u.getPuntos());
        if(u.getContrasena()!=null)usuario.setContrasena(u.getContrasena());
        if(u.getCuentaActiva()!=null)usuario.setCuentaActiva(u.getCuentaActiva());

        return userRepository.save(usuario);
    }

    @Override
    public User putPuntaje(Long id, Integer puntaje) {
        User usuario = userRepository.findById(id)
                .orElseThrow(()-> new ManejoErrores( "No se encontró un usuario con ese id"));

        usuario.setPuntos(puntaje);
        return userRepository.save(usuario);
    }
}
