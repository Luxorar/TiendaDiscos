package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Disco;
import com.TiendaDisco.ManejoStock.model.Sede;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DiscoRepositoryTest {

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @BeforeEach
    void setUp() {
        discoRepository.deleteAll();
        sedeRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Sede sede = sedeRepository.save(new Sede(null, "Sede Central", "Av. Principal 123"));
        Disco disco = new Disco();
        disco.setNombreProducto("Thriller");
        disco.setSede(sede);
        disco.setStockActual(10);
        disco.setArtista("Michael Jackson");

        Disco guardado = discoRepository.save(disco);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
