package com.TiendaDisco.RegistrarProductos.repository;

import com.TiendaDisco.RegistrarProductos.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
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
    void debeBuscarPorNombreProductoExistente() {
        productoRepository.save(new Producto(null, "Camiseta", "Nike", 25000));

        List<Producto> resultado = productoRepository.findByNombreProducto("Camiseta");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombreProducto()).isEqualTo("Camiseta");
    }

    @Test
    void debeRetornarListaVaciaCuandoNombreProductoNoExiste() {
        List<Producto> resultado = productoRepository.findByNombreProducto("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeBuscarPorMarca() {
        productoRepository.save(new Producto(null, "Zapatillas", "Adidas", 50000));

        List<Producto> resultado = productoRepository.findByMarca("Adidas");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getMarca()).isEqualTo("Adidas");
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Producto producto = new Producto(null, "Gorra", "DC", 15000);

        Producto guardado = productoRepository.save(producto);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
