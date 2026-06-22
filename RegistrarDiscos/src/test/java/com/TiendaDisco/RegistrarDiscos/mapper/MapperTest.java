package com.TiendaDisco.RegistrarDiscos.mapper;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidDiscoAndTitulo_ShouldMapAllFields() {
        Titulo titulo = Titulo.builder()
                .id(1L).titulo("Pop")
                .build();
        Disco disco = Disco.builder()
                .id(1L)
                .nombreDisco("Thriller")
                .artista("Michael Jackson")
                .precio(20000)
                .titulo(titulo)
                .build();

        DiscoDTO dto = Mapper.toDTO(disco);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Thriller", dto.getNombreDisco()),
                () -> assertEquals("Michael Jackson", dto.getArtista()),
                () -> assertEquals(20000, dto.getPrecio()),
                () -> assertEquals("Pop", dto.getTitulo())
        );
    }

    @Test
    void toDTO_WithValidDiscoAndNullTitulo_ShouldMapWithoutTitulo() {
        Disco disco = Disco.builder()
                .id(1L)
                .nombreDisco("Abbey Road")
                .artista("The Beatles")
                .precio(25000)
                .build();

        DiscoDTO dto = Mapper.toDTO(disco);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Abbey Road", dto.getNombreDisco()),
                () -> assertEquals("The Beatles", dto.getArtista()),
                () -> assertEquals(25000, dto.getPrecio()),
                () -> assertNull(dto.getTitulo())
        );
    }

    @Test
    void toDTO_WithNullDisco_ShouldReturnNull() {
        DiscoDTO dto = Mapper.toDTO(null);
        assertNull(dto);
    }
}
