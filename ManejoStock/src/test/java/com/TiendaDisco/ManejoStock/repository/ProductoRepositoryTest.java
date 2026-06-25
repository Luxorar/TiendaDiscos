package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Producto;
import com.TiendaDisco.ManejoStock.model.TipoProducto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);
        producto.setSede(1L);
        producto.setProducto(producto);

        Producto guardado = entityManager.persist(producto);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
        assertThat(guardado.getTipoProducto()).isEqualTo(TipoProducto.PRODUCTO);
        assertThat(guardado.getIdProducto()).isEqualTo(1L);
    }

    @Test
    void debeGuardarProductoConTipoDisco() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.DISCO);
        producto.setIdProducto(2L);
        producto.setSede(1L);
        producto.setProducto(producto);

        Producto guardado = entityManager.persist(producto);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getTipoProducto()).isEqualTo(TipoProducto.DISCO);
        assertThat(guardado.getIdProducto()).isEqualTo(2L);
    }
}
