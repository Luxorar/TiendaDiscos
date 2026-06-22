package com.TiendaDisco.RegistrarProductos.controller;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.model.Producto;
import com.TiendaDisco.RegistrarProductos.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarProductoPorId() throws Exception {
        ProductoDTO dto = ProductoDTO.builder()
                .id(1L).nombreProducto("Guitarra").marca("Fender").precio(500000).build();

        when(productoService.getProductoID(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Guitarra"))
                .andExpect(jsonPath("$.marca").value("Fender"));
    }

    @Test
    void debeRetornarProductosPorNombre() throws Exception {
        ProductoDTO dto = ProductoDTO.builder()
                .id(1L).nombreProducto("Guitarra").marca("Fender").precio(500000).build();

        when(productoService.getProductoNombre("Guitarra")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/productos/nombre/Guitarra"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreProducto").value("Guitarra"));
    }

    @Test
    void debeRetornarProductosPorMarca() throws Exception {
        ProductoDTO dto = ProductoDTO.builder()
                .id(1L).nombreProducto("Guitarra").marca("Fender").precio(500000).build();

        when(productoService.getProductoMarca("Fender")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/productos/marca/Fender"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("Fender"));
    }

    @Test
    void debeCrearProducto() throws Exception {
        Producto entrada = Producto.builder().nombreProducto("Guitarra").marca("Fender").precio(500000).build();
        Producto creado = Producto.builder().id(1L).nombreProducto("Guitarra").marca("Fender").precio(500000).build();

        when(productoService.postProducto(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreProducto").value("Guitarra"));
    }

    @Test
    void debeEliminarProducto() throws Exception {
        when(productoService.deleteProducto(1L)).thenReturn("Producto eliminado");

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Producto eliminado"));
    }
}
