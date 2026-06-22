package com.TiendaDisco.RegistrarDiscos.controller;

import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.service.TituloService;
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

@WebMvcTest(TituloController.class)
public class TituloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TituloService tituloService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarTituloPorId() throws Exception {
        Titulo titulo = new Titulo(1L, "Thriller", new ArrayList<>());

        when(tituloService.getTituloId(1L)).thenReturn(titulo);

        mockMvc.perform(get("/api/v1/titulos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Thriller"));
    }

    @Test
    void debeCrearTitulo() throws Exception {
        Titulo entrada = new Titulo(null, "Thriller", new ArrayList<>());
        Titulo creado = new Titulo(1L, "Thriller", new ArrayList<>());

        when(tituloService.postTitulo(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/titulos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Thriller"));
    }

    @Test
    void debeActualizarTitulo() throws Exception {
        Titulo actualizado = new Titulo(1L, "Thriller 25", new ArrayList<>());

        when(tituloService.putTitulo(any(Long.class), any())).thenReturn("Titulo actualizado");

        mockMvc.perform(put("/api/v1/titulos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Titulo actualizado"));
    }

    @Test
    void debeEliminarTitulo() throws Exception {
        when(tituloService.deleteTitulo(1L)).thenReturn("Titulo eliminado");

        mockMvc.perform(delete("/api/v1/titulos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Titulo eliminado"));
    }
}
