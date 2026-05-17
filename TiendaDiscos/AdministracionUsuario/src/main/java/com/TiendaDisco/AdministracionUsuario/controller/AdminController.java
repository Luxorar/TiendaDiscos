package com.TiendaDisco.AdministracionUsuario.controller;

import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<UserDTO> getAllUser() {
        return adminService.getAllUser();
    }

    @PostMapping
    public ResponseEntity<User> postUsuario(@Valid @RequestBody User u) {
        return ResponseEntity.ok(adminService.postUsuario(u));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserDTO> getUserId(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserId(id));
    }

    @GetMapping("name/{name}")
    public UserDTO getUserName(@PathVariable String name) {
        return adminService.getUserName(name);
    }

    @DeleteMapping("{id}")
    public void deleteUserId(@PathVariable Long id) {
        adminService.deleteUserId(id);
    }

    @PutMapping("{id}")
    public User putUser(@PathVariable Long id,@Valid @RequestBody User u) {
        return adminService.putUser(id, u);
    }

    @PutMapping("/id/{id}")
    public User putPuntaje(@PathVariable Long id,@RequestBody Integer puntaje) {
        return adminService.putPuntaje(id, puntaje);
    }
}
