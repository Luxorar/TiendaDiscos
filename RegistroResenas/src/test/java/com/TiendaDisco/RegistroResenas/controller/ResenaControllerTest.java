package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.service.ResenaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResenaController.class)
public class ResenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResenaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarResenaPorId() throws Exception {
        ResenaDTO dto = ResenaDTO.builder().id(1L).mensaje("Excelente disco").userName("Ana").nombreDisco("Thriller").build();

        when(service.getResenaId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/Resena/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Excelente disco"))
                .andExpect(jsonPath("$.userName").value("Ana"))
                .andExpect(jsonPath("$.nombreDisco").value("Thriller"));
    }

    @Test
    void debeCrearResena() throws Exception {
        Resena entrada = Resena.builder().mensaje("Excelente disco").build();
        Resena creado = Resena.builder().id(1L).mensaje("Excelente disco").build();

        when(service.postResena(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/Resena")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.mensaje").value("Excelente disco"));
    }

    @Test
    void debeEliminarResena() throws Exception {
        when(service.deleteResena(1L)).thenReturn("Resena eliminada");

        mockMvc.perform(delete("/api/v1/Resena/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Resena eliminada"));
    }
}
