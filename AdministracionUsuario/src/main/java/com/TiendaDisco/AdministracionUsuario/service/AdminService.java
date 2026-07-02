package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.exception.ManejoErrores;
import com.TiendaDisco.AdministracionUsuario.mapper.Mapper;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.repository.AdminRepository;
import com.TiendaDisco.AdministracionUsuario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementacion del servicio de administracion de usuarios.
 * <p>Contiene la logica de negocio para el CRUD de usuarios y
 * administradores, incluyendo validaciones y manejo de excepciones.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class AdminService implements IAdminService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

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
    public Admin postAdmin(Admin a) {
        a.setFechaRegistro(LocalDate.now());
        a.setCuentaActiva(true);

        return adminRepository.save(a) ;
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

    @Override
    public List<AdminDTO> getAllAdmin() {
        return adminRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public AdminDTO getAdminId(Long id) {
        Admin a = adminRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de administrador no encontrado: " + id));
        return Mapper.toDTO(a);
    }

    @Override
    public AdminDTO getAdminName(String name) {
        Admin a = adminRepository.findByUserName(name)
                .orElseThrow(() -> new ManejoErrores("No se encontró un administrador con ese nombre: " + name));
        return Mapper.toDTO(a);
    }

    @Override
    public void deleteAdminId(Long id) {
        Admin a = adminRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de administrador a eliminar no encontrado: " + id));
        adminRepository.delete(a);
    }

    @Override
    public Admin putAdmin(Long id, Admin a) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de administrador a modificar no encontrado: " + id));

        if(a.getUserName()!=null) admin.setUserName(a.getUserName());
        if(a.getContrasena()!=null) admin.setContrasena(a.getContrasena());
        if(a.getCuentaActiva()!=null) admin.setCuentaActiva(a.getCuentaActiva());

        return adminRepository.save(admin);
    }
}
