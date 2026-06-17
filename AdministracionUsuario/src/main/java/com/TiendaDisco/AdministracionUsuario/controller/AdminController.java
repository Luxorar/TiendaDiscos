package com.TiendaDisco.AdministracionUsuario.controller;

import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
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
    @PutMapping("/id/{id}")
    public User putPuntaje(@PathVariable Long id,@RequestBody Integer puntaje) {
        return adminService.putPuntaje(id, puntaje);
    }
}
