package com.TiendaDisco.AdministracionVentas.controller;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import com.TiendaDisco.AdministracionVentas.service.VentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarVentaPorId() throws Exception {
        VentaDTO dto = VentaDTO.builder()
                .id(1L).fechaVenta(LocalDate.now()).usuario("Ana")
                .puntosUsados(0).puntosGanados(10).subtotal(5000)
                .descuento(0).totalPagar(5000).build();

        when(ventaService.getVentaId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/ventas/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.usuario").value("Ana"));
    }

    @Test
    void debeRetornarVentasPorUsuario() throws Exception {
        VentaDTO dto = VentaDTO.builder()
                .id(1L).fechaVenta(LocalDate.now()).usuario("Ana")
                .puntosUsados(0).puntosGanados(10).subtotal(5000)
                .descuento(0).totalPagar(5000).build();

        when(ventaService.getVentaUser("Ana")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/ventas/user/Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuario").value("Ana"));
    }

    @Test
    void debeCrearVenta() throws Exception {
        Venta entrada = new Venta(null, null, LocalDate.now(), null, 0, 0, 0);
        VentaDTO creada = VentaDTO.builder()
                .id(1L).fechaVenta(LocalDate.now()).usuario("Ana")
                .puntosUsados(0).puntosGanados(10).subtotal(5000)
                .descuento(0).totalPagar(5000).build();

        when(ventaService.postVenta(any())).thenReturn(creada);

        mockMvc.perform(post("/api/v1/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void debeRetornarProductosDeVenta() throws Exception {
        Producto p = new Producto(1L, "Guitarra", 50000);
        when(ventaService.getProductoReciboId(1L)).thenReturn(List.of(p));

        mockMvc.perform(get("/api/v1/ventas/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Guitarra"));
    }

    @Test
    void debeEliminarVenta() throws Exception {
        doNothing().when(ventaService).delVenta(1L);

        mockMvc.perform(delete("/api/v1/ventas/1"))
                .andExpect(status().isOk());
    }
}
