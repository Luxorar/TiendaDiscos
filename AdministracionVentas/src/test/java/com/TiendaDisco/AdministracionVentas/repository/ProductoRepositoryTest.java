package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.TipoProducto;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Producto producto = new Producto(null, "Guitarra", 50000, null, TipoProducto.PRODUCTO);

        Producto guardado = productoRepository.save(producto);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }

    @Test
    void debeBuscarPorIdExistente() {
        Producto producto = productoRepository.save(new Producto(null, "Bateria", 80000, null, TipoProducto.PRODUCTO));

        Optional<Producto> resultado = productoRepository.findById(producto.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Bateria");
    }

    @Test
    void debeRetornarVacioCuandoIdNoExiste() {
        Optional<Producto> resultado = productoRepository.findById(999L);

        assertThat(resultado).isEmpty();
    }
}
