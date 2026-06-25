package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Producto;
import com.TiendaDisco.ManejoStock.model.TipoProducto;
import com.TiendaDisco.ManejoStock.model.infoStock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class InfoStockRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InfoStockRepository infoStockRepository;

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);
        producto.setSede(1L);
        producto.setProducto(producto);
        entityManager.persist(producto);

        infoStock item = new infoStock();
        item.setProducto(producto);
        item.setSede(1L);
        item.setStockActual(10);

        infoStock guardado = infoStockRepository.save(item);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
        assertThat(guardado.getStockActual()).isEqualTo(10);
    }

    @Test
    void debeGuardarYRecuperarStock() {
        Producto p = new Producto();
        p.setTipoProducto(TipoProducto.PRODUCTO);
        p.setIdProducto(1L);
        p.setSede(1L);
        p.setProducto(p);
        entityManager.persist(p);

        infoStock item = new infoStock();
        item.setProducto(p);
        item.setSede(1L);
        item.setStockActual(5);
        infoStockRepository.save(item);

        assertThat(infoStockRepository.count()).isEqualTo(2);
        assertThat(infoStockRepository.findAll())
                .extracting(infoStock::getStockActual)
                .contains(5);
    }
}
