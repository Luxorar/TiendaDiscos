package com.TiendaDisco.AdministracionUsuario.mapper;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidUser_ShouldMapAllFields() {
        User user = User.builder()
                .id(1L)
                .userName("Ana")
                .fechaRegistro(LocalDate.of(2025, 1, 10))
                .puntos(100)
                .build();

        UserDTO dto = Mapper.toDTO(user);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Ana", dto.getUserName()),
                () -> assertEquals(LocalDate.of(2025, 1, 10), dto.getFechaRegistro()),
                () -> assertEquals(100, dto.getPuntos())
        );
    }

    @Test
    void toDTO_WithNullUser_ShouldReturnNull() {
        UserDTO dto = Mapper.toDTO((User) null);
        assertNull(dto);
    }

    @Test
    void toDTO_WithValidAdmin_ShouldMapAllFields() {
        Admin admin = Admin.builder()
                .id(2L)
                .userName("Admin1")
                .fechaRegistro(LocalDate.of(2025, 3, 15))
                .build();

        AdminDTO dto = Mapper.toDTO(admin);

        assertAll(
                () -> assertEquals(2L, dto.getId()),
                () -> assertEquals("Admin1", dto.getUserName()),
                () -> assertEquals(LocalDate.of(2025, 3, 15), dto.getFechaRegistro())
        );
    }

    @Test
    void toDTO_WithNullAdmin_ShouldReturnNull() {
        AdminDTO dto = Mapper.toDTO((Admin) null);
        assertNull(dto);
    }
}
