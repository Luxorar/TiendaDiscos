package com.TiendaDisco.RegistrarSede.controller;

import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.model.Sede;
import com.TiendaDisco.RegistrarSede.service.SedeService;
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

@WebMvcTest(SedeController.class)
public class SedeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SedeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarSedePorId() throws Exception {
        SedeDTO dto = SedeDTO.builder().id(1L).nombreSede("Sede Central")
                .direccionSede("Av. Principal 123").numberSedeTelefono("123456789").build();

        when(service.getSedeId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/Sede/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreSede").value("Sede Central"));
    }

    @Test
    void debeCrearSede() throws Exception {
        Sede entrada = Sede.builder().nombreSede("Sede Central")
                .direccionSede("Av. Principal 123").numberSedeTelefono("123456789")
                .listProducto(new ArrayList<>()).listDisco(new ArrayList<>()).build();
        Sede creado = Sede.builder().id(1L).nombreSede("Sede Central")
                .direccionSede("Av. Principal 123").numberSedeTelefono("123456789")
                .listProducto(new ArrayList<>()).listDisco(new ArrayList<>()).build();

        when(service.postSede(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/Sede")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreSede").value("Sede Central"));
    }

    @Test
    void debeActualizarSede() throws Exception {
        Sede actualizado = Sede.builder().nombreSede("Sede Norte")
                .direccionSede("Av. Norte 456").numberSedeTelefono("987654321")
                .listProducto(new ArrayList<>()).listDisco(new ArrayList<>()).build();

        when(service.putSede(any(Long.class), any())).thenReturn("Sede actualizada");

        mockMvc.perform(put("/api/v1/Sede/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Sede actualizada"));
    }

    @Test
    void debeEliminarSede() throws Exception {
        when(service.deleteSedeId(1L)).thenReturn("Sede eliminada");

        mockMvc.perform(delete("/api/v1/Sede/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Sede eliminada"));
    }
}
