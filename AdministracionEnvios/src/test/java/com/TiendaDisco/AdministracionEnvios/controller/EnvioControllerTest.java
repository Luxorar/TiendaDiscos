package com.TiendaDisco.AdministracionEnvios.controller;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.model.TipoDespacho;
import com.TiendaDisco.AdministracionEnvios.service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeCrearEnvioYRetornar200() throws Exception {
        Envio envio = new Envio(1L, 1L, "Calle 123", TipoDespacho.CASA,
                "Correos", EstadoEnvio.EN_CAMINO, LocalDate.now());

        when(envioService.postEnvio(any())).thenReturn(envio);

        mockMvc.perform(post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.direccionDestino").value("Calle 123"));
    }

    @Test
    void debeActualizarEstadoEnvio() throws Exception {
        Envio actualizado = new Envio(1L, 1L, "Calle 123", TipoDespacho.CASA,
                "Correos", EstadoEnvio.ENTREGADO, LocalDate.now());

        when(envioService.PutEstadoEnvio(any(EstadoEnvio.class), any(Long.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/envios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(EstadoEnvio.ENTREGADO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estadoEnvio").value("ENTREGADO"));
    }

    @Test
    void debeActualizarDireccionEnvio() throws Exception {
        Envio actualizado = new Envio(1L, 1L, "Av. Nueva 456", TipoDespacho.CASA,
                "Correos", EstadoEnvio.EN_CAMINO, LocalDate.now());

        when(envioService.PutDirEnvio(any(String.class), any(Long.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/envios/dir/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("Av. Nueva 456")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccionDestino").value("Av. Nueva 456"));
    }

    @Test
    void debeEliminarEnvio() throws Exception {
        doNothing().when(envioService).deleteEnvio(1L);

        mockMvc.perform(delete("/api/v1/envios/1"))
                .andExpect(status().isOk());
    }
}
