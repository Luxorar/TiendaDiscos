package com.TiendaDisco.RegistroResenas.controller;

import com.TiendaDisco.RegistroResenas.DTO.DiscoDTO;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.service.DiscoService;
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

@WebMvcTest(com.TiendaDisco.RegistroResenas.controller.DiscoController.class)
public class DiscoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarDiscoPorId() throws Exception {
        DiscoDTO dto = DiscoDTO.builder().id(1L).nombreDisco("Thriller").artista("Michael Jackson").build();

        when(service.getDiscoId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/Disco/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDisco").value("Thriller"))
                .andExpect(jsonPath("$.artista").value("Michael Jackson"));
    }

    @Test
    void debeCrearDisco() throws Exception {
        Disco entrada = Disco.builder().nombreDisco("Thriller").artista("Michael Jackson")
                .resenaList(new ArrayList<>()).build();
        Disco creado = Disco.builder().id(1L).nombreDisco("Thriller").artista("Michael Jackson")
                .resenaList(new ArrayList<>()).build();

        when(service.postDisco(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/Disco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreDisco").value("Thriller"));
    }

    @Test
    void debeActualizarDisco() throws Exception {
        Disco actualizado = Disco.builder().nombreDisco("Thriller 25").artista("Michael Jackson")
                .resenaList(new ArrayList<>()).build();

        when(service.putDisco(any(Long.class), any())).thenReturn("Disco actualizado");

        mockMvc.perform(put("/api/v1/Disco/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Disco actualizado"));
    }

    @Test
    void debeEliminarDisco() throws Exception {
        when(service.deleteDisco(1L)).thenReturn("Disco eliminado");

        mockMvc.perform(delete("/api/v1/Disco/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Disco eliminado"));
    }
}
