package com.TiendaDisco.CarritoCompras.mapper;

import com.TiendaDisco.CarritoCompras.client.DiscoClient;
import com.TiendaDisco.CarritoCompras.client.ProductoClient;
import com.TiendaDisco.CarritoCompras.client.UserClient;
import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.dto.DiscoDTO;
import com.TiendaDisco.CarritoCompras.dto.ProductoDTO;
import com.TiendaDisco.CarritoCompras.dto.UserDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.CarritoDisco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
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
    void toDTO_WithValidCarrito_ShouldMapAllFields() {
        CarritoDisco cd = CarritoDisco.builder()
                .id(1L).discoId(1L).qty(1).build();
        Producto producto = Producto.builder()
                .id(1L).nombreProducto("Guitarra").precio(150000)
                .build();
        Carrito carrito = Carrito.builder()
                .id(1L)
                .userId(1L)
                .productosAgregados(List.of(producto))
                .discosAgregados(new ArrayList<>(List.of(cd)))
                .descuento(10.0)
                .build();

        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(
                DiscoDTO.builder().id(1L).nombreDisco("Thriller").artista("Michael Jackson").precio(20000).build()
        ));
        when(productoClient.obtenerProductoPorId(1L)).thenReturn(ResponseEntity.ok(
                ProductoDTO.builder().id(1L).nombreProducto("Guitarra").precio(150000).build()
        ));
        when(userClient.getUserId(1L)).thenReturn(
                UserDTO.builder().id(1L).userName("Ana").puntos(0).build()
        );

        CarritoDTO dto = mapper.toDTO(carrito);

        int sumaEsperada = 20000 + 150000;
        int precioLiquidoEsperado = (int) Math.round(sumaEsperada * (100.0 - 10.0) / 100.0);
        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Ana", dto.getUser()),
                () -> assertEquals(sumaEsperada, dto.getPrecioSolid()),
                () -> assertEquals(1, dto.getProductosAgregados().size()),
                () -> assertEquals(1, dto.getDiscosAgregados().size()),
                () -> assertEquals("Thriller", dto.getDiscosAgregados().get(0).getNombreDisco()),
                () -> assertEquals(10.0, dto.getDescuento(), 0.001),
                () -> assertEquals(precioLiquidoEsperado, dto.getPrecioLiquido())
        );
    }

    @Test
    void toDTO_WithNullCarrito_ShouldReturnNull() {
        CarritoDTO dto = mapper.toDTO(null);
        assertNull(dto);
    }
}
