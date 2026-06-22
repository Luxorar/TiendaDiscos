package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Sede;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SedeRepositoryTest {

    @Autowired
    private SedeRepository sedeRepository;

    @BeforeEach
    void setUp() {
        sedeRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Sede sede = Sede.builder()
                .nombreSede("Sede Central")
                .direccionSede("Av. Principal 123")
                .numberSedeTelefono("987654321")
                .build();

        Sede guardado = sedeRepository.save(sede);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Sede sede = sedeRepository.save(Sede.builder()
                .nombreSede("Sede Norte")
                .direccionSede("Av. Norte 456")
                .numberSedeTelefono("123456789")
                .build());

        Optional<Sede> resultado = sedeRepository.findById(sede.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombreSede()).isEqualTo("Sede Norte");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Sede> resultado = sedeRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
