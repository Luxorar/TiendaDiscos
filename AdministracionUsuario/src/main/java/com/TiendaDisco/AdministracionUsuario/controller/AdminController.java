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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la administracion de usuarios y administradores.
 * <p>Expone endpoints para registrar, consultar, actualizar y eliminar
 * usuarios y administradores del sistema.</p>
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

    @Operation(
            summary="Obtencion de usuarios",
            description="Obtiene a todos los usuarios"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Obtencion valida"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de {@link UserDTO}
     */
    @GetMapping
    public List<UserDTO> getAllUser() {
        return adminService.getAllUser();
    }

    @Operation(
            summary="Creacion usuario",
            description="Permite a un administrador crear un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Usuario creado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param u objeto {@link User} con los datos del usuario
     * @return el usuario creado
     */
    @PostMapping
    public ResponseEntity<User> postUsuario(@Valid @RequestBody User u) {
        return ResponseEntity.ok(adminService.postUsuario(u));
    }

    @Operation(
            summary="Obtencion por id",
            description="Permite ver un usuario segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Usuario obtenido"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return {@link ResponseEntity} con el {@link UserDTO}
     */
    @GetMapping("id/{id}")
    public ResponseEntity<UserDTO> getUserId(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserId(id));
    }

    @Operation(
            summary="Obtencion por nombre",
            description="Permite obtener un usuario segun su nombre"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Usuario obtenido"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param name nombre del usuario
     * @return {@link UserDTO} del usuario encontrado
     */
    @GetMapping("name/{name}")
    public UserDTO getUserName(@PathVariable String name) {
        return adminService.getUserName(name);
    }

    @Operation(
            summary="Borrar usuario",
            description="Permite borrar un usuario segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Usuario eliminado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar
     */
    @DeleteMapping("{id}")
    public void deleteUserId(@PathVariable Long id) {
        adminService.deleteUserId(id);
    }

    @Operation(
            summary="Actualizar usuario",
            description="Permite actualizar un usuario segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Usuario actualizado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id identificador del usuario
     * @param u  objeto {@link User} con los datos actualizados
     * @return el usuario actualizado
     */
    @PutMapping("{id}")
    public User putUser(@PathVariable Long id,@Valid @RequestBody User u) {
        return adminService.putUser(id, u);
    }

    @Operation(
            summary="Ingresar puntaje",
            description="Permite agregar puntos a un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Puntos agregados"),
            @ApiResponse(responseCode="400",
                    description="Dato invalido")
    })
    /**
     * Actualiza el puntaje de un usuario.
     *
     * @param id      identificador del usuario
     * @param puntaje nuevo puntaje a asignar
     * @return el usuario con el puntaje actualizado
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
    @Operation(
            summary="Obtener administradores",
            description="Obtiene todos los administradores registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Administradores obtenidos"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @GetMapping("/admins")
    public List<AdminDTO> getAllAdmin() {
        return adminService.getAllAdmin();
    }

    /**
     * Registra un nuevo administrador en el sistema.
     *
     * @param a objeto {@link Admin} con los datos del administrador
     * @return el administrador creado
     */
    @Operation(
            summary="Crear administrador",
            description="Permite registrar un nuevo administrador"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Administrador creado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @PostMapping("/admins")
    public ResponseEntity<Admin> postAdmin(@Valid @RequestBody Admin a) {
        return ResponseEntity.ok(adminService.postAdmin(a));
    }

    /**
     * Obtiene un administrador por su identificador.
     *
     * @param id identificador del administrador
     * @return {@link ResponseEntity} con el {@link AdminDTO}
     */
    @Operation(
            summary="Obtener administrador por id",
            description="Permite obtener un administrador segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Administrador obtenido"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminDTO> getAdminId(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminId(id));
    }

    /**
     * Obtiene un administrador por su nombre de usuario.
     *
     * @param name nombre del administrador
     * @return {@link ResponseEntity} con el {@link AdminDTO}
     */
    @Operation(
            summary="Obtener administrador por nombre",
            description="Permite obtener un administrador segun su nombre"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Administrador obtenido"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @GetMapping("/admins/name/{name}")
    public ResponseEntity<AdminDTO> getAdminName(@PathVariable String name) {
        return ResponseEntity.ok(adminService.getAdminName(name));
    }

    /**
     * Elimina un administrador por su identificador.
     *
     * @param id identificador del administrador a eliminar
     * @return {@link ResponseEntity} vacio
     */
    @Operation(
            summary="Eliminar administrador",
            description="Permite eliminar un administrador segun su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Administrador eliminado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> deleteAdminId(@PathVariable Long id) {
        adminService.deleteAdminId(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Actualiza los datos de un administrador existente.
     *
     * @param id identificador del administrador
     * @param a  objeto {@link Admin} con los datos actualizados
     * @return el administrador actualizado
     */
    @Operation(
            summary="Actualizar administrador",
            description="Permite actualizar los datos de un administrador"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Administrador actualizado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> putAdmin(@PathVariable Long id, @Valid @RequestBody Admin a) {
        return ResponseEntity.ok(adminService.putAdmin(id, a));
    }

    @Operation(
            summary="Agregar credito",
            description="Permite agregar o descontar credito a un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description="Credito actualizado"),
            @ApiResponse(responseCode="400", description="Dato invalido")
    })
    @PutMapping("/credito/{id}")
    public User putCredito(@PathVariable Long id, @RequestBody BigDecimal monto) {
        return adminService.addCredito(id, monto);
    }

    @Operation(
            summary="Actualizar modo oscuro",
            description="Permite activar o desactivar el modo oscuro de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description="Preferencia actualizada"),
            @ApiResponse(responseCode="400", description="Dato invalido")
    })
    @PutMapping("/modo-oscuro/{id}")
    public User putModoOscuro(@PathVariable Long id, @RequestBody Boolean modoOscuro) {
        return adminService.putModoOscuro(id, modoOscuro);
    }

    @Operation(
            summary="Login de usuario",
            description="Valida credenciales de usuario con gmail y contraseña"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description="Login exitoso"),
            @ApiResponse(responseCode="404", description="Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody Map<String, String> body) {
        String gmail = body.get("gmail");
        String contrasena = body.get("contrasena");
        return ResponseEntity.ok(adminService.loginUser(gmail, contrasena));
    }

    @Operation(
            summary="Login de administrador",
            description="Valida credenciales de administrador con gmail y contraseña"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description="Login exitoso"),
            @ApiResponse(responseCode="404", description="Credenciales incorrectas")
    })
    @PostMapping("/admins/login")
    public ResponseEntity<AdminDTO> loginAdmin(@RequestBody Map<String, String> body) {
        String gmail = body.get("gmail");
        String contrasena = body.get("contrasena");
        return ResponseEntity.ok(adminService.loginAdmin(gmail, contrasena));
    }

    @Operation(
            summary="Actualizar direccion predeterminada",
            description="Permite modificar la direccion predeterminada de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description="Direccion actualizada"),
            @ApiResponse(responseCode="400", description="Dato invalido")
    })
    @PutMapping(value = "/direccion/{id}", consumes = "text/plain")
    public User putDireccion(@PathVariable Long id, @RequestBody String direccion) {
        return adminService.putDireccion(id, direccion);
    }

    @Operation(
            summary="Actualizar telefono",
            description="Permite modificar el telefono de un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description="Telefono actualizado"),
            @ApiResponse(responseCode="400", description="Dato invalido")
    })
    @PutMapping(value = "/telefono/{id}", consumes = "text/plain")
    public User putTelefono(@PathVariable Long id, @RequestBody String telefono) {
        return adminService.putTelefono(id, telefono);
    }

    @ApiResponses({
            @ApiResponse(responseCode="200", description="User obtenido"),
            @ApiResponse(responseCode="400", description="Dato invalido")
    })
    @GetMapping("/info-user/{id}")
    public User getUserSinDTO(@PathVariable Long id) {
        return adminService.getCompleteUserId(id);
    }
}
