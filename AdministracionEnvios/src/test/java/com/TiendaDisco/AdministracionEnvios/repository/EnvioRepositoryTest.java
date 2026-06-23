package com.TiendaDisco.AdministracionEnvios.repository;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.model.TipoDespacho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository envioRepository;

    @BeforeEach
    void setUp() {
        envioRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Envio envio = Envio.builder()
                .ventaId(1L)
                .direccionDestino("Calle Falsa 123")
                .tipoDespacho(TipoDespacho.CASA)
                .empresaReparto("Correos")
                .estadoEnvio(EstadoEnvio.ENTREGADO)
                .fechaEntrega(LocalDate.of(2025, 6, 15))
                .build();

        Envio guardado = envioRepository.save(envio);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Envio envio = Envio.builder()
                .ventaId(1L)
                .direccionDestino("Av. Siempre Viva 742")
                .tipoDespacho(TipoDespacho.RETIRO_TIENDA)
                .empresaReparto("DHL")
                .estadoEnvio(EstadoEnvio.EN_CAMINO)
                .fechaEntrega(LocalDate.of(2025, 7, 20))
                .build();
        Envio guardado = envioRepository.save(envio);

        Optional<Envio> resultado = envioRepository.findById(guardado.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getDireccionDestino()).isEqualTo("Av. Siempre Viva 742");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Envio> resultado = envioRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
