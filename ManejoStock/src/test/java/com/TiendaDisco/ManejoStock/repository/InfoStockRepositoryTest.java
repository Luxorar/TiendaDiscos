package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Sede;
import com.TiendaDisco.ManejoStock.model.infoStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class InfoStockRepositoryTest {

    @Autowired
    private InfoStockRepository infoStockRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @BeforeEach
    void setUp() {
        infoStockRepository.deleteAll();
        sedeRepository.deleteAll();
    }

    @Test
    void debeBuscarPorSedeNombreSede() {
        Sede sede = sedeRepository.save(new Sede(null, "Sede Central", "Av. Principal 123"));
        infoStock item = new infoStock(null, "Camiseta", sede, 10);
        infoStockRepository.save(item);

        List<infoStock> resultado = infoStockRepository.findBySede_NombreSede("Sede Central");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombreProducto()).isEqualTo("Camiseta");
    }

    @Test
    void debeBuscarPorNombreProductoExistente() {
        Sede sede = sedeRepository.save(new Sede(null, "Sede Norte", "Av. Norte 456"));
        infoStock item = new infoStock(null, "Guitarra", sede, 5);
        infoStockRepository.save(item);

        Optional<infoStock> resultado = infoStockRepository.findByNombreProducto("Guitarra");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombreProducto()).isEqualTo("Guitarra");
    }

    @Test
    void debeRetornarVacioCuandoNombreProductoNoExiste() {
        Optional<infoStock> resultado = infoStockRepository.findByNombreProducto("NoExiste");

        assertThat(resultado).isEmpty();
    }
}
