package com.TiendaDisco.RegistrarSede.mapper;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.dto.ProductoDTO;
import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.model.Sede;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidSede_ShouldMapAllFields() {
        Sede sede = Sede.builder()
                .id(1L)
                .nombreSede("Sede Principal")
                .direccionSede("Av. Central 456")
                .numberSedeTelefono("123456789")
                .build();

        SedeDTO dto = Mapper.toDTO(sede);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Sede Principal", dto.getNombreSede()),
                () -> assertEquals("Av. Central 456", dto.getDireccionSede()),
                () -> assertEquals("123456789", dto.getNumberSedeTelefono())
        );
    }

    @Test
    void toDTO_WithNullSede_ShouldReturnNull() {
        SedeDTO dto = Mapper.toDTO((Sede) null);
        assertNull(dto);
    }

    @Test
    void toDTO_WithValidDisco_ShouldMapAllFields() {
        Disco disco = Disco.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson").precio(20000)
                .build();

        DiscoDTO dto = Mapper.toDTO(disco);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Thriller", dto.getNombreDisco()),
                () -> assertEquals("Michael Jackson", dto.getArtista()),
                () -> assertEquals(20000, dto.getPrecio())
        );
    }

    @Test
    void toDTO_WithNullDisco_ShouldReturnNull() {
        DiscoDTO dto = Mapper.toDTO((Disco) null);
        assertNull(dto);
    }

    @Test
    void toDTO_WithValidProducto_ShouldMapAllFields() {
        Producto producto = Producto.builder()
                .id(1L).nombreProducto("Amplificador").precio(80000)
                .build();

        ProductoDTO dto = Mapper.toDTO(producto);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Amplificador", dto.getNombreProducto()),
                () -> assertEquals(80000, dto.getPrecio())
        );
    }

    @Test
    void toDTO_WithNullProducto_ShouldReturnNull() {
        ProductoDTO dto = Mapper.toDTO((Producto) null);
        assertNull(dto);
    }
}
