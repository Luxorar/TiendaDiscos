package com.TiendaDisco.AdministracionDescuentos.repository;

import com.TiendaDisco.AdministracionDescuentos.Repository.DescuentoRepository;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DescuentoRepositoryTest {

    @Autowired
    private DescuentoRepository descuentoRepository;

    @BeforeEach
    void setUp() {
        descuentoRepository.deleteAll();
    }

    @Test
    void debeBuscarPorNombreExistente() {
        descuentoRepository.save(new Descuento(null, "CyberMonday", Estado.ACTIVO, null, null, 10));

        Optional<Descuento> resultado = descuentoRepository.findByNombre("CyberMonday");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("CyberMonday");
    }

    @Test
    void debeRetornarVacioCuandoNombreNoExiste() {
        Optional<Descuento> resultado = descuentoRepository.findByNombre("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Descuento descuento = new Descuento(null, "BlackFriday", Estado.ACTIVO, null, null, 20);

        Descuento guardado = descuentoRepository.save(descuento);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
