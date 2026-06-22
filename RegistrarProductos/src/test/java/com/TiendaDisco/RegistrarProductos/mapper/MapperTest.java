package com.TiendaDisco.RegistrarProductos.mapper;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidProducto_ShouldMapAllFields() {
        Producto producto = Producto.builder()
                .id(1L)
                .nombreProducto("Guitarra Eléctrica")
                .marca("Fender")
                .precio(150000)
                .build();

        ProductoDTO dto = Mapper.toDTO(producto);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Guitarra Eléctrica", dto.getNombreProducto()),
                () -> assertEquals("Fender", dto.getMarca()),
                () -> assertEquals(150000, dto.getPrecio())
        );
    }

    @Test
    void toDTOList_WithValidProductos_ShouldReturnList() {
        List<Producto> productos = List.of(
                Producto.builder().id(1L).nombreProducto("Guitarra").marca("Fender").precio(150000).build(),
                Producto.builder().id(2L).nombreProducto("Batería").marca("Yamaha").precio(250000).build()
        );

        List<ProductoDTO> dtos = Mapper.toDTOList(productos);

        assertEquals(2, dtos.size());
        assertEquals("Guitarra", dtos.get(0).getNombreProducto());
        assertEquals("Batería", dtos.get(1).getNombreProducto());
    }
}
