package com.TiendaDisco.AdministracionEnvios.mapper;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.model.TipoDespacho;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidEnvio_ShouldMapAllFields() {
        Envio envio = Envio.builder()
                .id(1L)
                .ventaId(100L)
                .direccionDestino("Av. Principal 123")
                .tipoDespacho(TipoDespacho.CASA)
                .empresaReparto("DHL")
                .estadoEnvio(EstadoEnvio.EN_CAMINO)
                .fechaEntrega(LocalDate.of(2025, 7, 15))
                .build();

        EnvioDTO dto = Mapper.toDTO(envio);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals(100L, dto.getVentaId()),
                () -> assertEquals("Av. Principal 123", dto.getDireccionDestino()),
                () -> assertEquals(TipoDespacho.CASA, dto.getTipoDespacho()),
                () -> assertEquals("DHL", dto.getEmpresaReparto()),
                () -> assertEquals(EstadoEnvio.EN_CAMINO, dto.getEstadoEnvio()),
                () -> assertEquals(LocalDate.of(2025, 7, 15), dto.getFechaEntrega())
        );
    }

    @Test
    void toDTO_WithNullEnvio_ShouldReturnNull() {
        EnvioDTO dto = Mapper.toDTO(null);
        assertNull(dto);
    }
}
