package com.TiendaDisco.AdministracionEnvios.service;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.exception.ManejoErrores;
import com.TiendaDisco.AdministracionEnvios.mapper.Mapper;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.model.TipoDespacho;
import com.TiendaDisco.AdministracionEnvios.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository repo;

    @InjectMocks
    private EnvioService envioService;

    private Envio createTestEnvio(Long id, EstadoEnvio estado) {
        return Envio.builder()
                .id(id)
                .ventaId(1L)
                .direccionDestino("Calle Falsa 123")
                .tipoDespacho(TipoDespacho.CASA)
                .empresaReparto("Correos")
                .estadoEnvio(estado)
                .fechaEntrega(LocalDate.of(2025, 6, 15))
                .build();
    }

    @Test
    void getAllEnvios_ShouldReturnEmptyList() {
        when(repo.findAll()).thenReturn(List.of());

        List<EnvioDTO> result = envioService.getAllEnvios();

        assertThat(result).isEmpty();
        verify(repo).findAll();
    }

    @Test
    void getAllEnvios_ShouldReturnAllEnvios() {
        List<Envio> envios = List.of(
                createTestEnvio(1L, EstadoEnvio.ENTREGADO),
                createTestEnvio(2L, EstadoEnvio.EN_CAMINO)
        );
        when(repo.findAll()).thenReturn(envios);

        List<EnvioDTO> result = envioService.getAllEnvios();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getEstadoEnvio()).isEqualTo(EstadoEnvio.ENTREGADO);
        assertThat(result.get(1).getEstadoEnvio()).isEqualTo(EstadoEnvio.EN_CAMINO);
        verify(repo).findAll();
    }

    @Test
    void postEnvio_ShouldReturnSavedEnvio() {
        Envio envio = createTestEnvio(null, EstadoEnvio.ENTREGADO);
        Envio saved = createTestEnvio(1L, EstadoEnvio.ENTREGADO);
        when(repo.save(envio)).thenReturn(saved);

        Envio result = envioService.postEnvio(envio);

        assertThat(result.getId()).isEqualTo(1L);
        verify(repo).save(envio);
    }

    @Test
    void postEnvio_ShouldReturnSavedEnvioWithAllFields() {
        Envio envio = Envio.builder()
                .ventaId(5L)
                .direccionDestino("Av. Siempre Viva 742")
                .tipoDespacho(TipoDespacho.RETIRO_TIENDA)
                .empresaReparto("DHL")
                .estadoEnvio(EstadoEnvio.ENTREGADO)
                .fechaEntrega(LocalDate.of(2025, 7, 20))
                .build();
        Envio saved = Envio.builder()
                .id(10L)
                .ventaId(5L)
                .direccionDestino("Av. Siempre Viva 742")
                .tipoDespacho(TipoDespacho.RETIRO_TIENDA)
                .empresaReparto("DHL")
                .estadoEnvio(EstadoEnvio.ENTREGADO)
                .fechaEntrega(LocalDate.of(2025, 7, 20))
                .build();
        when(repo.save(envio)).thenReturn(saved);

        Envio result = envioService.postEnvio(envio);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getVentaId()).isEqualTo(5L);
        assertThat(result.getDireccionDestino()).isEqualTo("Av. Siempre Viva 742");
        assertThat(result.getTipoDespacho()).isEqualTo(TipoDespacho.RETIRO_TIENDA);
        assertThat(result.getEmpresaReparto()).isEqualTo("DHL");
        assertThat(result.getEstadoEnvio()).isEqualTo(EstadoEnvio.ENTREGADO);
        assertThat(result.getFechaEntrega()).isEqualTo(LocalDate.of(2025, 7, 20));
        verify(repo).save(envio);
    }

    @Test
    void PutEstadoEnvio_ShouldUpdateEstado() {
        Envio envio = createTestEnvio(1L, EstadoEnvio.ENTREGADO);
        when(repo.findById(1L)).thenReturn(Optional.of(envio));
        when(repo.save(envio)).thenReturn(envio);

        Envio result = envioService.PutEstadoEnvio(EstadoEnvio.CANCELADO, 1L);

        assertThat(result.getEstadoEnvio()).isEqualTo(EstadoEnvio.CANCELADO);
        verify(repo).findById(1L);
        verify(repo).save(envio);
    }

    @Test
    void PutEstadoEnvio_ShouldUpdateEstadoToEnCamino() {
        Envio envio = createTestEnvio(1L, EstadoEnvio.ENTREGADO);
        when(repo.findById(1L)).thenReturn(Optional.of(envio));
        when(repo.save(envio)).thenReturn(envio);

        Envio result = envioService.PutEstadoEnvio(EstadoEnvio.EN_CAMINO, 1L);

        assertThat(result.getEstadoEnvio()).isEqualTo(EstadoEnvio.EN_CAMINO);
        verify(repo).findById(1L);
        verify(repo).save(envio);
    }

    @Test
    void PutEstadoEnvio_ShouldThrowWhenNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> envioService.PutEstadoEnvio(EstadoEnvio.CANCELADO, 99L))
                .isInstanceOf(ManejoErrores.class);
        verify(repo).findById(99L);
        verify(repo, never()).save(any());
    }

    @Test
    void PutDirEnvio_ShouldUpdateDireccion() {
        Envio envio = createTestEnvio(1L, EstadoEnvio.ENTREGADO);
        when(repo.findById(1L)).thenReturn(Optional.of(envio));
        when(repo.save(envio)).thenReturn(envio);

        Envio result = envioService.PutDirEnvio("Nueva Direccion 456", 1L);

        assertThat(result.getDireccionDestino()).isEqualTo("Nueva Direccion 456");
        verify(repo).findById(1L);
        verify(repo).save(envio);
    }

    @Test
    void PutDirEnvio_ShouldUpdateAndPreserveOtherFields() {
        Envio envio = createTestEnvio(1L, EstadoEnvio.ENTREGADO);
        when(repo.findById(1L)).thenReturn(Optional.of(envio));
        when(repo.save(envio)).thenReturn(envio);

        Envio result = envioService.PutDirEnvio("Nuevo Domicilio 789", 1L);

        assertThat(result.getDireccionDestino()).isEqualTo("Nuevo Domicilio 789");
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getVentaId()).isEqualTo(1L);
        assertThat(result.getTipoDespacho()).isEqualTo(TipoDespacho.CASA);
        assertThat(result.getEmpresaReparto()).isEqualTo("Correos");
        assertThat(result.getEstadoEnvio()).isEqualTo(EstadoEnvio.ENTREGADO);
        verify(repo).findById(1L);
        verify(repo).save(envio);
    }

    @Test
    void PutDirEnvio_ShouldThrowWhenNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> envioService.PutDirEnvio("dir", 99L))
                .isInstanceOf(ManejoErrores.class);
        verify(repo).findById(99L);
    }

    @Test
    void deleteEnvio_ShouldDeleteWhenExists() {
        when(repo.existsById(1L)).thenReturn(true);

        envioService.deleteEnvio(1L);

        verify(repo).existsById(1L);
        verify(repo).deleteById(1L);
    }

    @Test
    void deleteEnvio_ShouldThrowWhenNotFound() {
        when(repo.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> envioService.deleteEnvio(99L))
                .isInstanceOf(ManejoErrores.class);
        verify(repo).existsById(99L);
        verify(repo, never()).deleteById(any());
    }
}
