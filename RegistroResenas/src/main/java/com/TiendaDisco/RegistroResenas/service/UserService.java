package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.mapper.Mapper;
import com.TiendaDisco.RegistroResenas.model.User;
import com.TiendaDisco.RegistroResenas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la logica de negocio para los usuarios
 * Contiene las operaciones para registrar, validar y consultar a los usuarios.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository repo;

    /**
     * Registra y guarda un nuevo usuario en la base de datos.
     * * @param d El objeto {@link User} con la informacion a registrar.
     * @return El Usuario guardado junto con el ID autogenerado por la base de datos.
     */
    public User postUsuario(User u){
        return repo.save(u);
    }

    /**
     * Recupera una lista con todos los usuarios registrados actualmente en el sistema.
     * Cada usuario encontrado es mapeado a su DTO
     * @return Una coleccion {@link List} de {@link UserDTO}.
     */
    public List<UserDTO> getAllUsers(){
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    /**
     * Busca y obtiene la informacion de un usuario en base a su id.
     * La respuesta es transformada a un DTO
     * * @param id el id del usuario que se desea buscar
     * @return Un objeto {@link UserDTO} con la informacion del usuario solicitado.
     * @throws ManejoErrores Si no existe ningun usuario con el ID proporcionado.
     */
    public UserDTO getUserId(Long id){
        User user = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
        return Mapper.toDTO(user);
    }

    /**
     * Modifica los datos de un usuario existente en el sistema.
     * Actualiza el nombre de usuario y el gmail
     * * @param id El identificador unico del usuario que se va a modificar.
     * @param u El objeto {@link User} con los nuevos datos a actualizar.
     * @return Un mensaje de tipo String confirmando que se modifico el usuario.
     * @throws ManejoErrores Si el ID a modificar no es encontrado en la base de datos
     */
    public String putUsers(Long id, User u){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        usuario.setUserName(u.getUserName());
        usuario.setGmail(u.getGmail());
        return "Usuario modificado";
    }

    /**
     * Elimina permanentemente un usuario en la base de datos mediante su ID.
     * * @param id El identificador unico del usuario que se desea eliminar.
     * @return Un mensaje de tipo String confirmando que se elimino el usuario.
     * @throws ManejoErrores Si el id a eliminar no existe en la base de datos.
     */
    public String deleteUser(Long id){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(usuario);
        return "Usuario elimiando";
    }
}
