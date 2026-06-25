package com.TiendaDisco.RegistrarDiscos.controller;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.service.DiscoService;
import java.util.List;
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

@WebMvcTest(com.TiendaDisco.RegistrarDiscos.controller.DiscoController.class)
public class DiscoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscoService discoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarDiscoPorId() throws Exception {
        DiscoDTO dto = DiscoDTO.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson")
                .precio(15000).titulos(List.of("Thriller")).build();

        when(discoService.getDiscoId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDisco").value("Thriller"))
                .andExpect(jsonPath("$.artista").value("Michael Jackson"));
    }

    @Test
    void debeCrearDisco() throws Exception {
        Disco entrada = Disco.builder().nombreDisco("Thriller").artista("Michael Jackson").precio(15000).build();
        Disco creado = Disco.builder().id(1L).nombreDisco("Thriller").artista("Michael Jackson").precio(15000).build();

        when(discoService.postDisco(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreDisco").value("Thriller"));
    }

    @Test
    void debeActualizarDisco() throws Exception {
        Disco actualizado = Disco.builder().nombreDisco("Thriller 2").artista("Michael Jackson").precio(18000).build();

        when(discoService.putDisco(any(Long.class), any())).thenReturn("Disco actualizado");

        mockMvc.perform(put("/api/v1/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Disco actualizado"));
    }

    @Test
    void debeEliminarDisco() throws Exception {
        when(discoService.deleteDisco(1L)).thenReturn("Disco eliminado");

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Disco eliminado"));
    }
}
