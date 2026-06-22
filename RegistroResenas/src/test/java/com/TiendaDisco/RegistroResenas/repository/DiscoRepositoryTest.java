package com.TiendaDisco.RegistroResenas.repository;

import com.TiendaDisco.RegistroResenas.model.Disco;
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
        Disco disco = Disco.builder().nombreDisco("Thriller").artista("Michael Jackson").build();

        Disco guardado = discoRepository.save(disco);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Disco disco = discoRepository.save(Disco.builder().nombreDisco("Abbey Road").artista("The Beatles").build());

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
