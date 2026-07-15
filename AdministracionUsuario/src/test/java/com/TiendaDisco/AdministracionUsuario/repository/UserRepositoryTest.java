package com.TiendaDisco.AdministracionUsuario.repository;

import com.TiendaDisco.AdministracionUsuario.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    void debeBuscarPorUserNameExistente() {
        userRepository.save(new User(null, "Ana", "ana@mail.com", LocalDate.now(), 0, "pass", true, BigDecimal.ZERO, false, null, null));

        Optional<User> resultado = userRepository.findByUserName("Ana");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUserName()).isEqualTo("Ana");
    }

    @Test
    void debeRetornarVacioCuandoUserNameNoExiste() {
        Optional<User> resultado = userRepository.findByUserName("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        User usuario = new User(null, "Luis", "luis@mail.com", LocalDate.now(), 10, "pass", true, BigDecimal.ZERO, false, null, null);

        User guardado = userRepository.save(usuario);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
