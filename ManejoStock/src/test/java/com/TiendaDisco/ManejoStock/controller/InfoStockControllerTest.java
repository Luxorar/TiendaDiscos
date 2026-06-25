package com.TiendaDisco.ManejoStock.controller;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.model.Producto;
import com.TiendaDisco.ManejoStock.model.TipoProducto;
import com.TiendaDisco.ManejoStock.model.infoStock;
import com.TiendaDisco.ManejoStock.service.InfoStockService;
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

@WebMvcTest(InfoStockController.class)
public class InfoStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoStockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeRetornarStockPorId() throws Exception {
        InfoStockDTO dto = new InfoStockDTO(1L, "Guitarra", "Sede Central", 10);

        when(stockService.getInfoID(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/stock/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Guitarra"))
                .andExpect(jsonPath("$.stockActual").value(10));
    }

    @Test
    void debeRetornarStockPorProducto() throws Exception {
        InfoStockDTO dto = new InfoStockDTO(1L, "Guitarra", null, 10);

        when(stockService.getProductoInfo("Guitarra")).thenReturn(dto);

        mockMvc.perform(get("/api/v1/stock/producto/Guitarra"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Guitarra"));
    }

    @Test
    void debeRetornarStockPorSede() throws Exception {
        InfoStockDTO dto = new InfoStockDTO(1L, "Guitarra", "Sede Central", 10);

        when(stockService.getSedeInfo("Sede Central")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/stock/sede/Sede Central"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreProducto").value("Guitarra"));
    }

    @Test
    void debeCrearStock() throws Exception {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);

        infoStock entrada = new infoStock(null, producto, 1L, 10);
        infoStock creado = new infoStock(1L, producto, 1L, 10);

        when(stockService.postInfoStock(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.stockActual").value(10));
    }

    @Test
    void debeActualizarStock() throws Exception {
        when(stockService.putStock(1L, 20)).thenReturn("Stock actualizado");

        mockMvc.perform(put("/api/v1/stock/1/cantidad?nuevoStock=20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Stock actualizado"));
    }

    @Test
    void debeEliminarStock() throws Exception {
        when(stockService.deleteInfo(1L)).thenReturn("Stock eliminado");

        mockMvc.perform(delete("/api/v1/stock/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Stock eliminado"));
    }
}
