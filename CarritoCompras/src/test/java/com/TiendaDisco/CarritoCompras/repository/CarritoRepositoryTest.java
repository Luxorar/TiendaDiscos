package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.Carrito;
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

    @BeforeEach
    void setUp() {
        carritoRepository.deleteAll();
    }

    @Test
    void debeBuscarPorUserIdExistente() {
        Carrito carrito = Carrito.builder()
                .userId(1L)
                .productosAgregados(new java.util.ArrayList<>())
                .discosAgregados(new java.util.ArrayList<>())
                .build();
        carritoRepository.save(carrito);

        Optional<Carrito> resultado = carritoRepository.findByUserId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUserId()).isEqualTo(1L);
    }

    @Test
    void debeRetornarVacioCuandoUserIdNoExiste() {
        Optional<Carrito> resultado = carritoRepository.findByUserId(999L);

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Carrito carrito = Carrito.builder()
                .userId(2L)
                .productosAgregados(new java.util.ArrayList<>())
                .discosAgregados(new java.util.ArrayList<>())
                .build();

        Carrito guardado = carritoRepository.save(carrito);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
