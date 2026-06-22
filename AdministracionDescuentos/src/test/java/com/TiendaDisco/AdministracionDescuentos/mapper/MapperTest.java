package com.TiendaDisco.AdministracionDescuentos.mapper;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Disco;
import com.TiendaDisco.AdministracionDescuentos.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidDescuento_ShouldMapAllFields() {
        Disco disco = Disco.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson").precio(20000)
                .build();
        Producto producto = Producto.builder()
                .id(1L).nombreProducto("Guitarra").marca("Fender").precio(150000)
                .build();
        Descuento descuento = Descuento.builder()
                .id(1L)
                .nombre("Descuento 10%")
                .discosAgregados(List.of(disco))
                .productosAgregados(List.of(producto))
                .descuento(10.0)
                .build();

        DescuentoDTO dto = Mapper.toDTO(descuento);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Descuento 10%", dto.getNombre()),
                () -> assertEquals(1, dto.getDiscosAgregados().size()),
                () -> assertTrue(dto.getDiscosAgregados().get(0).contains("Michael Jackson")),
                () -> assertEquals(1, dto.getProductosAgregados().size()),
                () -> assertTrue(dto.getProductosAgregados().get(0).contains("Guitarra")),
                () -> assertEquals(10.0, dto.getDescuento(), 0.001)
        );
    }

    @Test
    void toDTO_WithNullDescuento_ShouldReturnNull() {
        DescuentoDTO dto = Mapper.toDTO(null);
        assertNull(dto);
    }
}
