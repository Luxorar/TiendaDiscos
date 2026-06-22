package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Producto producto = new Producto(null, "Camiseta", 25000);

        Producto guardado = productoRepository.save(producto);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Producto producto = productoRepository.save(new Producto(null, "Guitarra", 50000));

        Optional<Producto> resultado = productoRepository.findById(producto.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombreProducto()).isEqualTo("Guitarra");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Producto> resultado = productoRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
