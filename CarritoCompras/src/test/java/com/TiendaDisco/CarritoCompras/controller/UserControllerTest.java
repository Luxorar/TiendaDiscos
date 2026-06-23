package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.model.User;
import com.TiendaDisco.CarritoCompras.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarUsuarioPorId() throws Exception {
        User user = new User(1L, "Ana", "ana@mail.com", "pass", null);

        when(userService.getUserId(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/carrito/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana"));
    }

    @Test
    void debeCrearUsuario() throws Exception {
        User entrada = new User(null, "Ana", "ana@mail.com", "pass", null);
        User creado = new User(1L, "Ana", "ana@mail.com", "pass", null);

        when(userService.postUser(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/carrito/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void debeActualizarUsuario() throws Exception {
        User actualizado = new User(1L, "Ana Modificada", "ana@mail.com", "pass", null);

        when(userService.updateUser(any(), eq(1L))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/carrito/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana Modificada"));
    }

    @Test
    void debeEliminarUsuario() throws Exception {
        User eliminado = new User(1L, "Ana", "ana@mail.com", "pass", null);

        when(userService.deleteUser(1L)).thenReturn(eliminado);

        mockMvc.perform(delete("/api/v1/carrito/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana"));
    }
}
