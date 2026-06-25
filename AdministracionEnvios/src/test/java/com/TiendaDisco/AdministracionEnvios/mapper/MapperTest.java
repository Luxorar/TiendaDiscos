package com.TiendaDisco.AdministracionEnvios.mapper;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.DTO.VentaDTO;
import com.TiendaDisco.AdministracionEnvios.client.VentaClient;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.model.TipoDespacho;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Mock
    private VentaClient ventaClient;

    @InjectMocks
    private Mapper mapper;

    @Test
    void toDTO_WithValidEnvio_ShouldMapAllFields() {
        VentaDTO ventaEsperada = VentaDTO.builder()
                .id(100L)
                .productosComprados(null)
                .fechaVenta(null)
                .usuario(null)
                .puntosUsados(0)
                .puntosGanados(0)
                .subtotal(0)
                .descuento(0)
                .totalPagar(0)
                .build();

        Envio envio = Envio.builder()
                .id(1L)
                .ventaId(100L)
                .direccionDestino("Av. Principal 123")
                .tipoDespacho(TipoDespacho.CASA)
                .empresaReparto("DHL")
                .estadoEnvio(EstadoEnvio.EN_CAMINO)
                .fechaEntrega(LocalDate.of(2025, 7, 15))
                .build();

        when(ventaClient.getVentaId(100L)).thenReturn(ResponseEntity.ok(ventaEsperada));

        EnvioDTO dto = mapper.toDTO(envio);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertNotNull(dto.getVentaId()),
                () -> assertEquals(100L, dto.getVentaId().getId()),
                () -> assertEquals("Av. Principal 123", dto.getDireccionDestino()),
                () -> assertEquals(TipoDespacho.CASA, dto.getTipoDespacho()),
                () -> assertEquals("DHL", dto.getEmpresaReparto()),
                () -> assertEquals(EstadoEnvio.EN_CAMINO, dto.getEstadoEnvio()),
                () -> assertEquals(LocalDate.of(2025, 7, 15), dto.getFechaEntrega())
        );
    }

    @Test
    void toDTO_WithNullEnvio_ShouldReturnNull() {
        EnvioDTO dto = mapper.toDTO(null);
        assertNull(dto);
    }
}
