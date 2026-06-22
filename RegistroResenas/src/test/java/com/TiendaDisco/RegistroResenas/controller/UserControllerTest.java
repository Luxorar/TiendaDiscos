package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.User;
import com.TiendaDisco.RegistroResenas.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(com.TiendaDisco.RegistroResenas.controller.UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarUsuarioPorId() throws Exception {
        UserDTO dto = UserDTO.builder().id(1L).userName("Ana").gmail("ana@mail.com").build();

        when(service.getUserId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/User/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana"));
    }

    @Test
    void debeCrearUsuario() throws Exception {
        User entrada = User.builder().userName("Ana").gmail("ana@mail.com").resenaList(new ArrayList<>()).build();
        User creado = User.builder().id(1L).userName("Ana").gmail("ana@mail.com")
                .resenaList(new ArrayList<>()).build();

        when(service.postUsuario(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("Ana"));
    }

    @Test
    void debeActualizarUsuario() throws Exception {
        User actualizado = User.builder().userName("Ana Modificada").gmail("ana@mail.com")
                .resenaList(new ArrayList<>()).build();

        when(service.putUsers(any(Long.class), any())).thenReturn("Usuario actualizado");

        mockMvc.perform(put("/api/v1/User/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Usuario actualizado"));
    }

    @Test
    void debeEliminarUsuario() throws Exception {
        when(service.deleteUser(1L)).thenReturn("Usuario eliminado");

        mockMvc.perform(delete("/api/v1/User/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Usuario eliminado"));
    }
}
