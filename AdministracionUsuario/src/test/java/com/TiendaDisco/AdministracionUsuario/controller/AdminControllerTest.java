package com.TiendaDisco.AdministracionUsuario.controller;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.exception.ManejoErrores;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    //---------------------------- USUARIO ------------------------------------

    @Test
    void debeRetornarUsuarioPorId() throws Exception {
        UserDTO dto = new UserDTO(1L, "Ana", "ana@mail.com", LocalDate.now(), 100, BigDecimal.ZERO, false);

        when(adminService.getUserId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/admin/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana"))
                .andExpect(jsonPath("$.puntos").value(100));
    }

    @Test
    void debeRetornarUsuarioPorNombre() throws Exception {
        UserDTO dto = new UserDTO(1L, "Ana", "ana@mail.com", LocalDate.now(), 100, BigDecimal.ZERO, false);

        when(adminService.getUserName("Ana")).thenReturn(dto);

        mockMvc.perform(get("/api/v1/admin/name/Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana"));
    }

    @Test
    void debeCrearUsuario() throws Exception {
        User entrada = new User(null, "Ana", "ana@mail.com", LocalDate.now(),
                0, "pass", true, BigDecimal.ZERO, false);
        User creado = new User(1L, "Ana", "ana@mail.com", LocalDate.now(),
                0, "pass", true, BigDecimal.ZERO, false);

        when(adminService.postUsuario(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("Ana"));
    }

    @Test
    void debeActualizarUsuario() throws Exception {
        User actualizado = new User(1L, "Ana Modificada", "ana@mail.com", LocalDate.now(),
                50, "pass", true, BigDecimal.ZERO, false);

        when(adminService.putUser(any(Long.class), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/admin/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Ana Modificada"));
    }

    @Test
    void debeActualizarPuntaje() throws Exception {
        User actualizado = new User(1L, "Ana", "ana@mail.com", LocalDate.now(),
                200, "pass", true, BigDecimal.ZERO, false);

        when(adminService.putPuntaje(any(Long.class), any(Integer.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/admin/id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(200)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.puntos").value(200));
    }

    @Test
    void debeEliminarUsuario() throws Exception {
        doNothing().when(adminService).deleteUserId(1L);

        mockMvc.perform(delete("/api/v1/admin/1"))
                .andExpect(status().isOk());
    }

    //---------------------------- ADMIN --------------------------------------

    @Test
    void debeRetornarTodosLosAdministradores() throws Exception {
        List<AdminDTO> admins = List.of(
                new AdminDTO(1L, "Admin Uno", LocalDate.now()),
                new AdminDTO(2L, "Admin Dos", LocalDate.now())
        );

        when(adminService.getAllAdmin()).thenReturn(admins);

        mockMvc.perform(get("/api/v1/admin/admins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("Admin Uno"))
                .andExpect(jsonPath("$[1].userName").value("Admin Dos"));
    }

    @Test
    void debeRetornarAdminPorId() throws Exception {
        AdminDTO dto = new AdminDTO(1L, "Admin Central", LocalDate.now());

        when(adminService.getAdminId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/admin/admins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Admin Central"));
    }

    @Test
    void debeRetornarAdminPorNombre() throws Exception {
        AdminDTO dto = new AdminDTO(1L, "Admin Nombre", LocalDate.now());

        when(adminService.getAdminName("Admin Nombre")).thenReturn(dto);

        mockMvc.perform(get("/api/v1/admin/admins/name/Admin Nombre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Admin Nombre"));
    }

    @Test
    void debeCrearAdmin() throws Exception {
        Admin entrada = new Admin(null, "Admin Nuevo", "admin@mail.com",
                null, "pass", true);
        Admin creado = new Admin(1L, "Admin Nuevo", "admin@mail.com",
                null, "pass", true);

        when(adminService.postAdmin(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/admin/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("Admin Nuevo"));
    }

    @Test
    void debeActualizarAdmin() throws Exception {
        Admin actualizado = new Admin(1L, "Admin Modificado", "admin@mail.com",
                null, "nuevaPass", true);

        when(adminService.putAdmin(any(Long.class), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/admin/admins/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Admin Modificado"));
    }

    @Test
    void debeEliminarAdmin() throws Exception {
        doNothing().when(adminService).deleteAdminId(1L);

        mockMvc.perform(delete("/api/v1/admin/admins/1"))
                .andExpect(status().isOk());
    }

    //---------------------------- EXCEPCIONES --------------------------------

    @Test
    void debeRetornar404CuandoUsuarioNoExiste() throws Exception {
        when(adminService.getUserId(99L)).thenThrow(new ManejoErrores("id no encontrado"));

        mockMvc.perform(get("/api/v1/admin/id/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("id no encontrado"));
    }

    @Test
    void debeRetornar400CuandoDatosUsuarioInvalidos() throws Exception {
        User invalido = new User(null, "", "", null, null, null, null, null, null);

        mockMvc.perform(post("/api/v1/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void debeRetornar404CuandoAdminNoExiste() throws Exception {
        when(adminService.getAdminId(99L)).thenThrow(new ManejoErrores("Id de administrador no encontrado: 99"));

        mockMvc.perform(get("/api/v1/admin/admins/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Id de administrador no encontrado: 99"));
    }
}
