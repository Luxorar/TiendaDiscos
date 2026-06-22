package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Producto;
import com.TiendaDisco.ManejoStock.model.Sede;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
        sedeRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Sede sede = sedeRepository.save(new Sede(null, "Sede Central", "Av. Principal 123"));
        Producto producto = new Producto();
        producto.setNombreProducto("Camiseta");
        producto.setSede(sede);
        producto.setStockActual(10);
        producto.setMarca("Nike");

        Producto guardado = productoRepository.save(producto);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
