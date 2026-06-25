package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.TipoProducto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        ventaRepository.deleteAll();
        productoRepository.deleteAll();
        producto = productoRepository.save(new Producto(null, "Test Producto", 10000, null, TipoProducto.PRODUCTO));
    }

    @Test
    void debeBuscarPorUsuarioId() {
        ventaRepository.save(Venta.builder()
                .usuario(1L)
                .fechaVenta(LocalDate.now())
                .puntosGanados(10)
                .build());

        List<Venta> resultado = ventaRepository.findByUsuario(1L);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getUsuario()).isEqualTo(1L);
    }

    @Test
    void debeRetornarListaVaciaCuandoNoExistenVentas() {
        List<Venta> resultado = ventaRepository.findByUsuario(99L);

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Venta venta = Venta.builder()
                .usuario(2L)
                .fechaVenta(LocalDate.now())
                .puntosGanados(20)
                .build();

        Venta guardado = ventaRepository.save(venta);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
