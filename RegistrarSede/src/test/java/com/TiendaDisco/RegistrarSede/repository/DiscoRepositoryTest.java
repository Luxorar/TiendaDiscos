package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Disco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DiscoRepositoryTest {

    @Autowired
    private DiscoRepository discoRepository;

    @BeforeEach
    void setUp() {
        discoRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Disco disco = new Disco(null, "Thriller", "Michael Jackson", 25000);

        Disco guardado = discoRepository.save(disco);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Disco disco = discoRepository.save(new Disco(null, "Abbey Road", "The Beatles", 20000));

        Optional<Disco> resultado = discoRepository.findById(disco.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombreDisco()).isEqualTo("Abbey Road");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Disco> resultado = discoRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
