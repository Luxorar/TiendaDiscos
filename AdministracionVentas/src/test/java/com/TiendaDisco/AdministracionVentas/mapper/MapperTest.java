package com.TiendaDisco.AdministracionVentas.mapper;

import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Usuario;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidVenta_ShouldMapAllFields() {
        Usuario usuario = new Usuario(1L, "Ana", "ana@mail.com");
        Producto p1 = new Producto(1L, "Guitarra", 50000);
        Producto p2 = new Producto(2L, "Vinilo", 15000);
        Venta venta = Venta.builder()
                .id(1L)
                .productosComprados(List.of(p1, p2))
                .fechaVenta(LocalDate.of(2025, 6, 1))
                .usuario(usuario)
                .puntosUsados(10)
                .puntosGanados(50)
                .descuento(5)
                .build();

        VentaDTO dto = Mapper.toDTO(venta);

        int subTotalEsperado = 50000 + 15000;
        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals(2, dto.getProductosComprados().size()),
                () -> assertTrue(dto.getProductosComprados().get(0).contains("Guitarra")),
                () -> assertEquals(LocalDate.of(2025, 6, 1), dto.getFechaVenta()),
                () -> assertEquals("ana@mail.com", dto.getUsuario()),
                () -> assertEquals(10, dto.getPuntosUsados()),
                () -> assertEquals(50, dto.getPuntosGanados()),
                () -> assertEquals(subTotalEsperado, dto.getSubtotal()),
                () -> assertEquals(5, dto.getDescuento()),
                () -> assertEquals((int) Math.round(subTotalEsperado * 5), dto.getTotalPagar())
        );
    }

    @Test
    void toDTO_WithNullVenta_ShouldReturnNull() {
        VentaDTO dto = Mapper.toDTO(null);
        assertNull(dto);
    }
}
