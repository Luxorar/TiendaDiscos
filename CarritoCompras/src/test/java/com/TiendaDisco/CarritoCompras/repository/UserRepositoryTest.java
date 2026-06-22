package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.User;
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
        User user = userRepository.save(new User(null, "Luis", "luis@mail.com", "pass", null));

        assertThat(user.getId()).isNotNull();
        assertThat(user.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        User user = userRepository.save(new User(null, "Ana", "ana@mail.com", "pass", null));

        Optional<User> resultado = userRepository.findById(user.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUserName()).isEqualTo("Ana");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<User> resultado = userRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
