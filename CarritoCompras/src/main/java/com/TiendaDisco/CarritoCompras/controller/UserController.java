package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.User;
import com.TiendaDisco.CarritoCompras.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.postUser(user));
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserId(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User user) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
