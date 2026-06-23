package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Usuario usuario = new Usuario(null, "Luis", "luis@mail.com");

        Usuario guardado = usuarioRepository.save(usuario);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Ana", "ana@mail.com"));

        Optional<Usuario> resultado = usuarioRepository.findById(usuario.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUserName()).isEqualTo("Ana");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Usuario> resultado = usuarioRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
