package com.TiendaDisco.AdministracionVentas.mapper;

import com.TiendaDisco.AdministracionVentas.client.DiscoClient;
import com.TiendaDisco.AdministracionVentas.client.ProductoClient;
import com.TiendaDisco.AdministracionVentas.client.UserClient;
import com.TiendaDisco.AdministracionVentas.dto.DiscoDTO;
import com.TiendaDisco.AdministracionVentas.dto.ProductoDTO;
import com.TiendaDisco.AdministracionVentas.dto.UserDTO;
import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.TipoProducto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Mock
    private DiscoClient discoClient;

    @Mock
    private ProductoClient productoClient;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private Mapper mapper;

    @Test
    void toDTO_WithValidVenta_ShouldMapAllFields() {
        Producto p1 = new Producto(1L, "Guitarra", 50000, 1L, TipoProducto.PRODUCTO);
        Producto p2 = new Producto(2L, "Vinilo", 15000, 2L, TipoProducto.DISCO);
        Venta venta = Venta.builder()
                .id(1L)
                .productosComprados(List.of(p1, p2))
                .fechaVenta(LocalDate.of(2025, 6, 1))
                .usuario(1L)
                .puntosUsados(10)
                .puntosGanados(50)
                .descuento(5)
                .build();

        ProductoDTO productoDTO = new ProductoDTO(1L, "Guitarra", "MarcaX", 50000);
        DiscoDTO discoDTO = new DiscoDTO(2L, "Vinilo", "ArtistaY", 15000);
        UserDTO userDTO = new UserDTO(1L, "ana@mail.com", LocalDate.of(2024, 1, 1), 0);

        when(productoClient.obtenerProductoPorId(1L)).thenReturn(ResponseEntity.ok(productoDTO));
        when(discoClient.obtenerDiscoPorId(2L)).thenReturn(ResponseEntity.ok(discoDTO));
        when(userClient.getUserId(1L)).thenReturn(userDTO);

        VentaDTO dto = mapper.toDTO(venta);

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
                () -> assertEquals((int) Math.round(subTotalEsperado * (100.0 - 5) / 100.0), dto.getTotalPagar())
        );
    }

    @Test
    void toDTO_WithNullVenta_ShouldReturnNull() {
        VentaDTO dto = mapper.toDTO(null);
        assertNull(dto);
    }
}
