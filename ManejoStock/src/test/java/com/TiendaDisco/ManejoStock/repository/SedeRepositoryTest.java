package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Sede;
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
    void debeBuscarPorNombreSedeExistente() {
        Sede sede = new Sede(null, "Sede Central", "Av. Principal 123");
        sedeRepository.save(sede);

        Optional<Sede> resultado = sedeRepository.findByNombreSede("Sede Central");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombreSede()).isEqualTo("Sede Central");
    }

    @Test
    void debeRetornarVacioCuandoNombreSedeNoExiste() {
        Optional<Sede> resultado = sedeRepository.findByNombreSede("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Sede sede = new Sede(null, "Sede Sur", "Av. Sur 789");

        Sede guardado = sedeRepository.save(sede);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
