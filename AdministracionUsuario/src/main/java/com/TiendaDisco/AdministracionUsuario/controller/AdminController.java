package com.TiendaDisco.AdministracionUsuario.controller;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la administracion de usuarios y administradores.
 * <p>Expone endpoints para registrar, consultar, actualizar y eliminar
 * tanto usuarios como administradores del sistema.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/v1/admin")
@Tag(
        name="Admin",
        description="Administrador de los usuarios"
)
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de {@link UserDTO}
     */
    @GetMapping
    public List<UserDTO> getAllUser() {
        return adminService.getAllUser();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param u objeto {@link User} con los datos del usuario
     * @return {@link ResponseEntity} con el usuario persistido
     */
    @PostMapping
    public ResponseEntity<User> postUsuario(@Valid @RequestBody User u) {
        return ResponseEntity.ok(adminService.postUsuario(u));
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return {@link ResponseEntity} con el {@link UserDTO} correspondiente
     */
    @GetMapping("id/{id}")
    public ResponseEntity<UserDTO> getUserId(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserId(id));
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param name nombre del usuario
     * @return {@link UserDTO} con los datos del usuario
     */
    @GetMapping("name/{name}")
    public UserDTO getUserName(@PathVariable String name) {
        return adminService.getUserName(name);
    }

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario
     */
    @DeleteMapping("{id}")
    public void deleteUserId(@PathVariable Long id) {
        adminService.deleteUserId(id);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id identificador del usuario
     * @param u  objeto {@link User} con los datos a actualizar
     * @return {@link User} actualizado
     */
    @PutMapping("{id}")
    public User putUser(@PathVariable Long id,@Valid @RequestBody User u) {
        return adminService.putUser(id, u);
    }

    /**
     * Actualiza el puntaje de un usuario.
     *
     * @param id     identificador del usuario
     * @param puntaje nuevo puntaje del usuario
     * @return {@link User} con el puntaje actualizado
     */
    @PutMapping("/id/{id}")
    public User putPuntaje(@PathVariable Long id,@RequestBody Integer puntaje) {
        return adminService.putPuntaje(id, puntaje);
    }

    /**
     * Obtiene todos los administradores registrados.
     *
     * @return lista de {@link AdminDTO}
     */
    @GetMapping("/admins")
    public List<AdminDTO> getAllAdmin() {
        return adminService.getAllAdmin();
    }

    /**
     * Registra un nuevo administrador.
     *
     * @param a objeto {@link Admin} con los datos del administrador
     * @return {@link ResponseEntity} con el administrador persistido
     */
    @PostMapping("/admins")
    public ResponseEntity<Admin> postAdmin(@Valid @RequestBody Admin a) {
        return ResponseEntity.ok(adminService.postAdmin(a));
    }

    /**
     * Obtiene un administrador por su identificador.
     *
     * @param id identificador del administrador
     * @return {@link ResponseEntity} con el {@link AdminDTO} correspondiente
     */
    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminDTO> getAdminId(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminId(id));
    }

    /**
     * Obtiene un administrador por su nombre.
     *
     * @param name nombre del administrador
     * @return {@link ResponseEntity} con el {@link AdminDTO} correspondiente
     */
    @GetMapping("/admins/name/{name}")
    public ResponseEntity<AdminDTO> getAdminName(@PathVariable String name) {
        return ResponseEntity.ok(adminService.getAdminName(name));
    }

    /**
     * Elimina un administrador por su identificador.
     *
     * @param id identificador del administrador
     * @return {@link ResponseEntity} con estado 200
     */
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> deleteAdminId(@PathVariable Long id) {
        adminService.deleteAdminId(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Actualiza los datos de un administrador.
     *
     * @param id identificador del administrador
     * @param a  objeto {@link Admin} con los datos a actualizar
     * @return {@link ResponseEntity} con el administrador actualizado
     */
    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> putAdmin(@PathVariable Long id, @Valid @RequestBody Admin a) {
        return ResponseEntity.ok(adminService.putAdmin(id, a));
    }
}
