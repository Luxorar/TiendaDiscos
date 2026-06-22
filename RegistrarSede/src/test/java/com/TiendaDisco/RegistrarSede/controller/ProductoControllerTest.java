package com.TiendaDisco.RegistrarSede.controller;

import com.TiendaDisco.RegistrarSede.dto.ProductoDTO;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.service.ProductoService;
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

@WebMvcTest(com.TiendaDisco.RegistrarSede.controller.ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarProductoPorId() throws Exception {
        ProductoDTO dto = ProductoDTO.builder().id(1L).nombreProducto("Guitarra").precio(50000).build();

        when(service.getProductoId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/Producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Guitarra"));
    }

    @Test
    void debeCrearProducto() throws Exception {
        Producto entrada = Producto.builder().nombreProducto("Guitarra").precio(50000).build();
        Producto creado = Producto.builder().id(1L).nombreProducto("Guitarra").precio(50000).build();

        when(service.postProducto(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/Producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreProducto").value("Guitarra"));
    }

    @Test
    void debeActualizarProducto() throws Exception {
        Producto actualizado = Producto.builder().nombreProducto("Bajo").precio(60000).build();

        when(service.putProducto(any(Long.class), any())).thenReturn("Producto actualizado");

        mockMvc.perform(put("/api/v1/Producto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Producto actualizado"));
    }

    @Test
    void debeEliminarProducto() throws Exception {
        when(service.deleteProducto(1L)).thenReturn("Producto eliminado");

        mockMvc.perform(delete("/api/v1/Producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Producto eliminado"));
    }
}
