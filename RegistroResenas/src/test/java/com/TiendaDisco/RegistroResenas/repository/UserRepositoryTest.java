package com.TiendaDisco.RegistroResenas.repository;

import com.TiendaDisco.RegistroResenas.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        User user = User.builder().userName("Ana").gmail("ana@mail.com").build();

        User guardado = userRepository.save(user);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        User user = userRepository.save(User.builder().userName("Luis").gmail("luis@mail.com").build());

        Optional<User> resultado = userRepository.findById(user.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUserName()).isEqualTo("Luis");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<User> resultado = userRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
