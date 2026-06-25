package com.TiendaDisco.ManejoStock.mapper;

import com.TiendaDisco.ManejoStock.DTO.DiscoDTO;
import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.DTO.ProductoDTO;
import com.TiendaDisco.ManejoStock.DTO.SedeDTO;
import com.TiendaDisco.ManejoStock.client.DiscoClient;
import com.TiendaDisco.ManejoStock.client.ProductoClient;
import com.TiendaDisco.ManejoStock.client.SedeClient;
import com.TiendaDisco.ManejoStock.model.Producto;
import com.TiendaDisco.ManejoStock.model.TipoProducto;
import com.TiendaDisco.ManejoStock.model.infoStock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Mock
    private SedeClient sedeClient;

    @Mock
    private ProductoClient productoClient;

    @Mock
    private DiscoClient discoClient;

    @InjectMocks
    private Mapper mapper;

    @Test
    void toDTO_WithProducto_ShouldMapAllFields() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setIdProducto(1L);
        producto.setTipoProducto(TipoProducto.PRODUCTO);

        infoStock stock = new infoStock(1L, producto, 10L, 50);

        ProductoDTO productoDTO = new ProductoDTO(1L, "Guitarra Acústica", "Fender", 1500);
        SedeDTO sedeDTO = SedeDTO.builder().id(10L).nombreSede("Sede Principal").direccionSede("Av. Central").build();

        when(productoClient.obtenerProductoPorId(1L)).thenReturn(ResponseEntity.ok(productoDTO));
        when(sedeClient.getSedeId(10L)).thenReturn(sedeDTO);

        InfoStockDTO dto = mapper.toDTO(stock);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertTrue(dto.getNombreProducto().contains("Guitarra Acústica")),
                () -> assertTrue(dto.getNombreProducto().contains("Fender")),
                () -> assertEquals("Sede Principal", dto.getNombreSede()),
                () -> assertEquals(50, dto.getStockActual())
        );
    }

    @Test
    void toDTO_WithDisco_ShouldMapAllFields() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setIdProducto(1L);
        producto.setTipoProducto(TipoProducto.DISCO);

        infoStock stock = new infoStock(1L, producto, 10L, 30);

        DiscoDTO discoDTO = new DiscoDTO(1L, "Thriller", "Michael Jackson", 20);
        SedeDTO sedeDTO = SedeDTO.builder().id(10L).nombreSede("Sede Principal").direccionSede("Av. Central").build();

        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(discoDTO));
        when(sedeClient.getSedeId(10L)).thenReturn(sedeDTO);

        InfoStockDTO dto = mapper.toDTO(stock);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertTrue(dto.getNombreProducto().contains("Thriller")),
                () -> assertTrue(dto.getNombreProducto().contains("Michael Jackson")),
                () -> assertEquals("Sede Principal", dto.getNombreSede()),
                () -> assertEquals(30, dto.getStockActual())
        );
    }

    @Test
    void toDTO_WithNullInfoStock_ShouldReturnNull() {
        InfoStockDTO dto = mapper.toDTO(null);
        assertNull(dto);
    }
}
