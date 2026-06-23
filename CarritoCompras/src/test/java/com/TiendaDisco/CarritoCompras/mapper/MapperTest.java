package com.TiendaDisco.CarritoCompras.mapper;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidCarrito_ShouldMapAllFields() {
        User user = User.builder()
                .id(1L).userName("Ana").gmail("ana@mail.com").password("pass")
                .build();
        Disco disco = Disco.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson").precio(20000)
                .build();
        Producto producto = Producto.builder()
                .id(1L).nombreProducto("Guitarra").precio(150000)
                .build();
        List<Disco> discos = new ArrayList<>();
        discos.add(disco);
        Carrito carrito = Carrito.builder()
                .id(1L)
                .user(user)
                .productosAgregados(List.of(producto))
                .discosAgregados((ArrayList<Disco>) discos)
                .descuento(10.0)
                .build();

        CarritoDTO dto = Mapper.toDTO(carrito);

        int sumaEsperada = 20000 + 150000;
        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Ana", dto.getUser()),
                () -> assertEquals(sumaEsperada, dto.getPrecioSolid()),
                () -> assertEquals(1, dto.getProductosAgregados().size()),
                () -> assertEquals(1, dto.getDiscosAgregados().size()),
                () -> assertEquals(10.0, dto.getDescuento(), 0.001),
                () -> assertEquals((int) Math.round(sumaEsperada), dto.getPrecioLiquido())
        );
    }

    @Test
    void toDTO_WithNullCarrito_ShouldReturnNull() {
        CarritoDTO dto = Mapper.toDTO(null);
        assertNull(dto);
    }
}
