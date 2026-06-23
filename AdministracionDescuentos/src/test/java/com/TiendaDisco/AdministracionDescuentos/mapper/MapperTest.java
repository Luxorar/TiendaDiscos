package com.TiendaDisco.AdministracionDescuentos.mapper;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.DTO.DiscoDTO;
import com.TiendaDisco.AdministracionDescuentos.DTO.ProductoDTO;
import com.TiendaDisco.AdministracionDescuentos.client.DiscoClient;
import com.TiendaDisco.AdministracionDescuentos.client.ProductoClient;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Estado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Mock
    private DiscoClient discoClient;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private Mapper mapper;

    @Test
    void toDTO_WithValidDescuento_ShouldMapBasicFields() {
        Descuento descuento = Descuento.builder()
                .id(1L)
                .nombre("Descuento 10%")
                .estado(Estado.ACTIVO)
                .discoIds(new ArrayList<>())
                .productoIds(new ArrayList<>())
                .descuento(10.0)
                .build();

        DescuentoDTO dto = mapper.toDTO(descuento);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Descuento 10%", dto.getNombre()),
                () -> assertEquals(Estado.ACTIVO, dto.getEstado()),
                () -> assertEquals(10.0, dto.getDescuento(), 0.001),
                () -> assertTrue(dto.getDiscosAgregados().isEmpty()),
                () -> assertTrue(dto.getProductosAgregados().isEmpty())
        );
    }

    @Test
    void toDTO_WithNullDescuento_ShouldReturnNull() {
        DescuentoDTO dto = mapper.toDTO(null);
        assertNull(dto);
    }

    @Test
    void toDTO_WithDiscos_ShouldEnrichWithFeignData() {
        List<Long> discoIds = new ArrayList<>();
        discoIds.add(1L);
        discoIds.add(2L);

        Descuento descuento = Descuento.builder()
                .id(1L)
                .nombre("Descuento 10%")
                .estado(Estado.ACTIVO)
                .discoIds(discoIds)
                .productoIds(new ArrayList<>())
                .descuento(10.0)
                .build();

        DiscoDTO disco1 = new DiscoDTO(1L, "Thriller", "Michael Jackson", 15000);
        DiscoDTO disco2 = new DiscoDTO(2L, "Back in Black", "AC/DC", 12000);
        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(disco1));
        when(discoClient.obtenerDiscoPorId(2L)).thenReturn(ResponseEntity.ok(disco2));

        DescuentoDTO dto = mapper.toDTO(descuento);

        List<String> discos = dto.getDiscosAgregados();
        assertTrue(discos.contains("Michael Jackson - Thriller"));
        assertTrue(discos.contains("AC/DC - Back in Black"));
        assertEquals(2, discos.size());
    }

    @Test
    void toDTO_WithProductos_ShouldEnrichWithFeignData() {
        List<Long> productoIds = new ArrayList<>();
        productoIds.add(10L);

        Descuento descuento = Descuento.builder()
                .id(1L)
                .nombre("Descuento 10%")
                .estado(Estado.ACTIVO)
                .discoIds(new ArrayList<>())
                .productoIds(productoIds)
                .descuento(10.0)
                .build();

        ProductoDTO producto = new ProductoDTO(10L, "Guitarra", "Fender", 150000);
        when(productoClient.obtenerProductoPorId(10L)).thenReturn(ResponseEntity.ok(producto));

        DescuentoDTO dto = mapper.toDTO(descuento);

        List<String> productos = dto.getProductosAgregados();
        assertTrue(productos.contains("Guitarra - Fender"));
        assertEquals(1, productos.size());
    }

}
