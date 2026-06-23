package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.User;
import com.TiendaDisco.RegistroResenas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/User")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public User postUser(@RequestBody User user){
        return service.postUsuario(user);
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDTO getUserId(@Valid @PathVariable Long id){
        return service.getUserId(id);
    }

    @PutMapping("{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user){
        return service.putUsers(id, user);
    }

    @DeleteMapping("{id}")
    public String deleteUserId(@PathVariable Long id){
        return service.deleteUser(id);
    }
}
