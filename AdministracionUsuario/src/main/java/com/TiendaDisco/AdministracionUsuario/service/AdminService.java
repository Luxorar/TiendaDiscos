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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Implementacion del servicio de administracion de usuarios.
 * Contiene la logica de negocio para gestionar usuarios y administradores del sistema.
 */
@Service
public class AdminService implements IAdminService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Obtiene todos los usuarios registrados y los convierte a DTO.
     *
     * @return lista de UserDTO
     */
    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    /**
     * Registra un nuevo usuario con fecha actual y cuenta activa por defecto.
     *
     * @param u objeto User a persistir
     * @return el usuario persistido
     */
    @Override
    public User postUsuario(User u) {
        u.setFechaRegistro(LocalDate.now());
        u.setCuentaActiva(true);

        return userRepository.save(u) ;
    }

    /**
     * Registra un nuevo administrador con fecha actual y cuenta activa por defecto.
     *
     * @param a objeto Admin a persistir
     * @return el administrador persistido
     */
    @Override
    public Admin postAdmin(Admin a) {
        a.setFechaRegistro(LocalDate.now());
        a.setCuentaActiva(true);

        return adminRepository.save(a) ;
    }

    /**
     * Busca un usuario por id, lanzando excepcion si no existe.
     *
     * @param id identificador del usuario
     * @return UserDTO del usuario encontrado
     */
    @Override
    public UserDTO getUserId(Long id) {
        User u =userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("id no encontrado"));

        return Mapper.toDTO(u);
    }

    /**
     * Busca un usuario por id, lanzando excepcion si no existe. Devuelve toda la información del ususario
     * sin transforma a DTO
     *
     * @param id identificador del usuario
     * @return User del usuario encontrado
     */
    @Override
    public User getCompleteUserId(Long id) {
        User u =userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("id no encontrado"));

        return u;
    }

    /**
     * Busca un usuario por nombre, validando que la cuenta este activa.
     *
     * @param name nombre del usuario
     * @return UserDTO del usuario encontrado, o null si esta inactivo
     */
    @Override
    public UserDTO getUserName(String name) {
        User u = userRepository.findByUserName(name)
                .orElseThrow(()-> new ManejoErrores( "No se encontr\u00f3 un usuario con ese nombre"));
        if(u.getCuentaActiva()==true) {
            return Mapper.toDTO(u);
        }else {
            return null;
        }
    }

    /**
     * Elimina un usuario por su id, lanzando excepcion si no existe.
     *
     * @param id identificador del usuario
     */
    @Override
    public void deleteUserId(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(()-> new ManejoErrores( "No se encontr\u00f3 un usuario con ese nombre"));

        userRepository.delete(u);
    }

    /**
     * Actualiza los campos modificables de un usuario existente.
     *
     * @param id identificador del usuario
     * @param u  objeto con los campos a actualizar
     * @return el usuario actualizado
     */
    @Override
    public User putUser(Long id, User u) {
        User usuario = userRepository.findById(id)
                .orElseThrow(()-> new ManejoErrores( "No se encontr\u00f3 un usuario con ese id"));

        if(u.getUserName()!=null)usuario.setUserName(u.getUserName());
        if(u.getPuntos()!=null)usuario.setPuntos(u.getPuntos());
        if(u.getContrasena()!=null)usuario.setContrasena(u.getContrasena());
        if(u.getCuentaActiva()!=null)usuario.setCuentaActiva(u.getCuentaActiva());
        if(u.getCredito()!=null) usuario.setCredito(u.getCredito());
        if(u.getModoOscuro()!=null) usuario.setModoOscuro(u.getModoOscuro());
        if(u.getDireccionPredeterminada()!=null) usuario.setDireccionPredeterminada(u.getDireccionPredeterminada());
        if(u.getTelefono()!=null) usuario.setTelefono(u.getTelefono());

        return userRepository.save(usuario);
    }

    /**
     * Actualiza unicamente el puntaje de un usuario.
     *
     * @param id      identificador del usuario
     * @param puntaje nuevo puntaje
     * @return el usuario con el puntaje actualizado
     */
    @Override
    public User putPuntaje(Long id, Integer puntaje) {
        User usuario = userRepository.findById(id)
                .orElseThrow(()-> new ManejoErrores( "No se encontr\u00f3 un usuario con ese id"));

        usuario.setPuntos(puntaje);
        return userRepository.save(usuario);
    }

    @Override
    public User addCredito(Long id, BigDecimal monto) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("No se encontró un usuario con ese id"));

        BigDecimal actual = usuario.getCredito() != null ? usuario.getCredito() : BigDecimal.ZERO;
        usuario.setCredito(actual.add(monto));
        return userRepository.save(usuario);
    }

    @Override
    public User putModoOscuro(Long id, Boolean modoOscuro) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("No se encontró un usuario con ese id"));

        usuario.setModoOscuro(modoOscuro);
        return userRepository.save(usuario);
    }

    @Override
    public User putDireccion(Long id, String direccion) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("No se encontró un usuario con ese id"));

        usuario.setDireccionPredeterminada(direccion);
        return userRepository.save(usuario);
    }

    @Override
    public User putTelefono(Long id, String telefono) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("No se encontró un usuario con ese id"));

        usuario.setTelefono(telefono);
        return userRepository.save(usuario);
    }
    /**
     * Obtiene todos los administradores y los convierte a DTO.
     *
     * @return lista de AdminDTO
     */
    @Override
    public List<AdminDTO> getAllAdmin() {
        return adminRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    /**
     * Busca un administrador por id, lanzando excepcion si no existe.
     *
     * @param id identificador del administrador
     * @return AdminDTO del administrador encontrado
     */
    @Override
    public AdminDTO getAdminId(Long id) {
        Admin a = adminRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de administrador no encontrado: " + id));
        return Mapper.toDTO(a);
    }

    /**
     * Busca un administrador por nombre, lanzando excepcion si no existe.
     *
     * @param name nombre del administrador
     * @return AdminDTO del administrador encontrado
     */
    @Override
    public AdminDTO getAdminName(String name) {
        Admin a = adminRepository.findByUserName(name)
                .orElseThrow(() -> new ManejoErrores("No se encontr\u00f3 un administrador con ese nombre: " + name));
        return Mapper.toDTO(a);
    }

    /**
     * Elimina un administrador por su id, lanzando excepcion si no existe.
     *
     * @param id identificador del administrador
     */
    @Override
    public void deleteAdminId(Long id) {
        Admin a = adminRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de administrador a eliminar no encontrado: " + id));
        adminRepository.delete(a);
    }

    /**
     * Actualiza los campos modificables de un administrador existente.
     *
     * @param id identificador del administrador
     * @param a  objeto con los campos a actualizar
     * @return el administrador actualizado
     */
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
