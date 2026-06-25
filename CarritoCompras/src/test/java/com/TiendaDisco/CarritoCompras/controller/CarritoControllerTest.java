package com.TiendaDisco.CarritoCompras.controller;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.service.CarritoService;
import com.TiendaDisco.CarritoCompras.service.DiscoService;
import com.TiendaDisco.CarritoCompras.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarritoController.class)
public class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private DiscoService discoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarCarritoPorUsuario() throws Exception {
        CarritoDTO dto = CarritoDTO.builder()
                .id(1L).user("Ana").precioSolid(10000)
                .productosAgregados(new ArrayList<>())
                .discosAgregados(new ArrayList<>())
                .descuento(0).precioLiquido(10000).build();

        when(carritoService.getCarrito(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/carrito/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("Ana"));
    }

    @Test
    void debeCrearCarrito() throws Exception {
        Carrito entrada = Carrito.builder()
                .userId(1L)
                .productosAgregados(new ArrayList<>())
                .discosAgregados(new ArrayList<>())
                .build();
        Carrito creado = Carrito.builder()
                .id(1L)
                .userId(1L)
                .productosAgregados(new ArrayList<>())
                .discosAgregados(new ArrayList<>())
                .build();

        when(carritoService.postCarrito(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/carrito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void debeAgregarDiscoAlCarrito() throws Exception {
        Disco disco = new Disco(1L, "Thriller", "Michael Jackson", 15000);

        when(discoService.postDisco(eq(1L), eq(1L), any())).thenReturn(disco);

        mockMvc.perform(post("/api/v1/carrito/1/discos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disco)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDisco").value("Thriller"));
    }

    @Test
    void debeEliminarDiscoDelCarrito() throws Exception {
        when(discoService.deleteDiscos(1L, 1L)).thenReturn("Disco eliminado");

        mockMvc.perform(delete("/api/v1/carrito/1/discos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Disco eliminado"));
    }

    @Test
    void debeEliminarCarrito() throws Exception {
        doNothing().when(carritoService).deleteCarrito(1L);

        mockMvc.perform(delete("/api/v1/carrito/1"))
                .andExpect(status().isOk());
    }
}
