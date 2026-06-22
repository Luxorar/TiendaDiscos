package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Usuario;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        ventaRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    void debeBuscarPorUsuarioUserName() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Ana", "ana@mail.com"));
        ventaRepository.save(Venta.builder()
                .usuario(usuario)
                .fechaVenta(LocalDate.now())
                .puntosGanados(10)
                .build());

        List<Venta> resultado = ventaRepository.findByUsuarioUserName("Ana");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getUsuario().getUserName()).isEqualTo("Ana");
    }

    @Test
    void debeRetornarListaVaciaCuandoNoExistenVentas() {
        List<Venta> resultado = ventaRepository.findByUsuarioUserName("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Luis", "luis@mail.com"));
        Venta venta = Venta.builder()
                .usuario(usuario)
                .fechaVenta(LocalDate.now())
                .puntosGanados(20)
                .build();

        Venta guardado = ventaRepository.save(venta);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
