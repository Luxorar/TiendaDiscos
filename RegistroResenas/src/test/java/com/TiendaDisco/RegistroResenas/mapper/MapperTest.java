package com.TiendaDisco.RegistroResenas.mapper;

import com.TiendaDisco.RegistroResenas.DTO.DiscoDTO;
import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidResena_ShouldMapAllFields() {
        User user = User.builder()
                .id(1L).userName("Ana").gmail("ana@mail.com")
                .build();
        Disco disco = Disco.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson")
                .build();
        Resena resena = Resena.builder()
                .id(1L)
                .user(user)
                .disco(disco)
                .mensaje("Excelente disco")
                .build();

        ResenaDTO dto = Mapper.toDTO(resena);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Ana", dto.getUserName()),
                () -> assertEquals("Thriller", dto.getNombreDisco()),
                () -> assertEquals("Excelente disco", dto.getMensaje())
        );
    }

    @Test
    void toDTO_WithNullResena_ShouldReturnNull() {
        ResenaDTO dto = Mapper.toDTO((Resena) null);
        assertNull(dto);
    }

    @Test
    void toDTO_WithValidUser_ShouldMapAllFields() {
        User user = User.builder()
                .id(1L).userName("Ana").gmail("ana@mail.com")
                .build();

        UserDTO dto = Mapper.toDTO(user);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Ana", dto.getUserName()),
                () -> assertEquals("ana@mail.com", dto.getGmail())
        );
    }

    @Test
    void toDTO_WithNullUser_ShouldReturnNull() {
        UserDTO dto = Mapper.toDTO((User) null);
        assertNull(dto);
    }

    @Test
    void toDTO_WithValidDisco_ShouldMapAllFields() {
        Disco disco = Disco.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson")
                .build();

        DiscoDTO dto = Mapper.toDTO(disco);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Thriller", dto.getNombreDisco()),
                () -> assertEquals("Michael Jackson", dto.getArtista())
        );
    }

    @Test
    void toDTO_WithNullDisco_ShouldReturnNull() {
        DiscoDTO dto = Mapper.toDTO((Disco) null);
        assertNull(dto);
    }
}
