package com.TiendaDisco.AdministracionDescuentos.controller;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.exception.ManejoErrores;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Estado;
import com.TiendaDisco.AdministracionDescuentos.service.DescuentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DescuentoController.class)
public class DescuentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DescuentoService descuentoService;

    @Autowired
    private ObjectMapper objectMapper;

    //------------------------------Get /api/v1/descuentos/{id}------------------------------

    @Test
    void debeRetornarun200deDescuentos() throws Exception {
        DescuentoDTO descuentoDTO = new DescuentoDTO(1L,"Descuento de ejemplo",
                Estado.ACTIVO,null,null,0.1
        );

        when(descuentoService.getDescuentoId(1L)).thenReturn(descuentoDTO);

        mockMvc.perform(get("/api/v1/descuentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Descuento de ejemplo"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"))
                .andExpect(jsonPath("$.descuento").value(0.1));
    }

    @Test
    void debeRetornar404CuandoNoExiste() throws Exception {
        when(descuentoService.getDescuentoId(99L))
                .thenThrow(new ManejoErrores("No encontrado"));

        mockMvc.perform(get("/api/v1/descuentos/99"))
                .andExpect(status().isNotFound());
    }

    //----------------------------POST-------------------------------------------------------------------

    @Test
    void debeCrearDescuentoYRetornar201() throws Exception {

        Descuento entrada = new Descuento(1L, "Descuento de ejemplo",
                Estado.ACTIVO, null, null, 0.1
        );

        Descuento creado = new Descuento(2L, "Descuento de ejemplo-2",
                Estado.INACTIVO, null, null, 0.2
        );

        when(descuentoService.postDescuento(any())).thenReturn(creado);

        mockMvc.perform(post("/api/v1/descuentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Descuento de ejemplo-2"))
                .andExpect(jsonPath("$.estado").value("INACTIVO"))
                .andExpect(jsonPath("$.descuento").value(0.2));
    }

    @Test
    void debeRetornar400FaltaEstado() throws Exception {

        String body = """
            { "nombre": "Descuento mal", "estado": null, "descuento": 0.5 }
            """;

        mockMvc.perform(post("/api/v1/descuentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void debeRetornar400FaltaNombre() throws Exception {
        String body = """
            { "nombre": null, "estado": "ACTIVO", "descuento": null }
            """;

        mockMvc.perform(post("/api/v1/descuentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    //--------------------------------------DELETE----------------------------------

    @Test
    void debeEliminarYRetornar204() throws Exception {

        when(descuentoService.deleteDescuento(1L)).thenReturn("Descuento eliminado exitosamente");

        mockMvc.perform(delete("/api/v1/descuentos/1"))
                .andExpect(status().isOk());
    }

    //------------------------------agregar disco------------------------------------------------

    @Test
    void debeAgregarDiscoAlDescuento() throws Exception {
        when(descuentoService.agregarDisco("Verano", 100L))
                .thenReturn("Disco agregado al descuento exitosamente");

        mockMvc.perform(post("/api/v1/descuentos/descuento/Verano")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("100"))
                .andExpect(status().isOk());
    }

    //------------------------------quitar disco--------------------------------------------------

    @Test
    void debeQuitarDiscoDelDescuento() throws Exception {
        when(descuentoService.quitarDisco("Verano", 100L))
                .thenReturn("Disco eliminado del descuento exitosamente");

        mockMvc.perform(delete("/api/v1/descuentos/descuento/Verano")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("100"))
                .andExpect(status().isOk());
    }

    //------------------------------agregar producto-----------------------------------------------

    @Test
    void debeAgregarProductoAlDescuento() throws Exception {
        when(descuentoService.agregarProducto("Verano", 200L))
                .thenReturn("Producto agregado al descuento exitosamente");

        mockMvc.perform(post("/api/v1/descuentos/producto/Verano")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk());
    }

    //------------------------------quitar producto------------------------------------------------

    @Test
    void debeQuitarProductoDelDescuento() throws Exception {
        when(descuentoService.quitarProducto("Verano", 200L))
                .thenReturn("Producto eliminado del descuento exitosamente");

        mockMvc.perform(delete("/api/v1/descuentos/producto/Verano")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk());
    }

}
