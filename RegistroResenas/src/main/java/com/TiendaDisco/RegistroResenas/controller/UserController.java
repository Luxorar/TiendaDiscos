package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.User;
import com.TiendaDisco.RegistroResenas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST que expone los endpoints para la gestion de los usuarios.
 * Se encarga de procesar las peticiones http y delegar la logica al servicio correspondiente.
 * * Ruta base: /api/v1/User
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/v1/User")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * Endpoint para registrar un nuevo isiarop en el sistema.
     * * @param user Objeto con los datos del user a registrar
     * @return El user persistido
     */
    @PostMapping
    public User postUser(@RequestBody User user){
        return service.postUsuario(user);
    }

    /**
     * Endpoint para obtener todos los usuarios registrados.
     * * @return Una respuesta HTTP 200 con la lista completa de usuarios en formato DTO.
     */
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }

    /**
     * Endpoint para buscar la información detallada de un usuario específico.
     * * @param id El identificador único del usuario enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO del usuario, o 400/404 si hay un error.
     */
    @GetMapping("{id}")
    public UserDTO getUserId(@Valid @PathVariable Long id){
        return service.getUserId(id);
    }

    /**
     * Endpoint para modificar los datos de un usuario ya existente.
     * * @param id El identificador del disco a modificar.
     * @param user El objeto con los nuevos datos del usuario.
     * @return Un mensaje de confirmación en formato String.
     */
    @PutMapping("{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user){
        return service.putUsers(id, user);
    }

    /**
     * Endpoint para eliminar un usuario del catálogo.
     * * @param id El identificador del usuario que se desea borrar.
     * @return Un mensaje de confirmación de la eliminación.
     */
    @DeleteMapping("{id}")
    public String deleteUserId(@PathVariable Long id){
        return service.deleteUser(id);
    }
}
