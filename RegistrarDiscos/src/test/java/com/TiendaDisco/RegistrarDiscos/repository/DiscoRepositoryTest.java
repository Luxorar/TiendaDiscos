package com.TiendaDisco.RegistrarDiscos.repository;

import com.TiendaDisco.RegistrarDiscos.model.Disco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        Disco disco = Disco.builder().nombreDisco("Thriller").artista("Michael Jackson").precio(25000).build();

        Disco guardado = discoRepository.save(disco);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
