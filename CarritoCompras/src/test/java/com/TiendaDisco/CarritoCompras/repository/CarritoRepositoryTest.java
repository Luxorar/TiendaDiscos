package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarritoRepositoryTest {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        carritoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void debeBuscarPorUserUserNameExistente() {
        User user = userRepository.save(new User(null, "Ana", "ana@mail.com", "pass", null));
        carritoRepository.save(Carrito.builder().user(user).build());

        Optional<Carrito> resultado = carritoRepository.findByUserUserName("Ana");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUser().getUserName()).isEqualTo("Ana");
    }

    @Test
    void debeRetornarVacioCuandoUserUserNameNoExiste() {
        Optional<Carrito> resultado = carritoRepository.findByUserUserName("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        User user = userRepository.save(new User(null, "Luis", "luis@mail.com", "pass", null));
        Carrito carrito = Carrito.builder().user(user).build();

        Carrito guardado = carritoRepository.save(carrito);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
