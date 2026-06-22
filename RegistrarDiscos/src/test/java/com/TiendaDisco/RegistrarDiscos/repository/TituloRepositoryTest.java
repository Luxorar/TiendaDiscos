package com.TiendaDisco.RegistrarDiscos.repository;

import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TituloRepositoryTest {

    @Autowired
    private TituloRepository tituloRepository;

    @BeforeEach
    void setUp() {
        tituloRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Titulo titulo = Titulo.builder().titulo("Thriller").build();

        Titulo guardado = tituloRepository.save(titulo);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Titulo titulo = tituloRepository.save(Titulo.builder().titulo("Abbey Road").build());

        Optional<Titulo> resultado = tituloRepository.findById(titulo.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getTitulo()).isEqualTo("Abbey Road");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Titulo> resultado = tituloRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
