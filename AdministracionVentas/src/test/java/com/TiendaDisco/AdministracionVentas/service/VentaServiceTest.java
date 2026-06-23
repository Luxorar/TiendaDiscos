package com.TiendaDisco.AdministracionVentas.service;
import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.exception.ManejoErrores;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.Usuario;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import com.TiendaDisco.AdministracionVentas.repository.ProductoRepository;
import com.TiendaDisco.AdministracionVentas.repository.UsuarioRepository;
import com.TiendaDisco.AdministracionVentas.repository.VentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private VentaService ventaService;

    private Producto createProducto(Long id, String nombre, int precio) {
        return Producto.builder()
                .id(id)
                .nombre(nombre)
                .precio(precio)
                .build();
    }

    private Usuario createUsuario() {
        return Usuario.builder()
                .id(1L)
                .userName("testuser")
                .gmail("test@test.com")
                .build();
    }

    private Venta createTestVenta(Long id) {
        List<Producto> productos = List.of(
                createProducto(1L, "Disco A", 10000),
                createProducto(2L, "Disco B", 15000)
        );
        return Venta.builder()
                .id(id)
                .productosComprados(productos)
                .fechaVenta(LocalDate.of(2025, 6, 1))
                .usuario(createUsuario())
                .puntosUsados(100)
                .puntosGanados(50)
                .descuento(10)
                .build();
    }

    @Test
    void getAllVentas_ShouldReturnAllVentas() {
        List<Venta> ventas = List.of(
                createTestVenta(1L),
                createTestVenta(2L)
        );
        when(ventaRepository.findAll()).thenReturn(ventas);

        List<VentaDTO> result = ventaService.getAllVentas();

        assertThat(result).hasSize(2);
        verify(ventaRepository).findAll();
    }

    @Test
    void getVentaId_ShouldReturnVentaDTO() {
        Venta venta = createTestVenta(1L);
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        VentaDTO result = ventaService.getVentaId(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUsuario()).isEqualTo("test@test.com");
        assertThat(result.getPuntosUsados()).isEqualTo(100);
        verify(ventaRepository).findById(1L);
    }

    @Test
    void getVentaId_ShouldThrowWhenNotFound() {
        when(ventaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ventaService.getVentaId(99L))
                .isInstanceOf(ManejoErrores.class);
        verify(ventaRepository).findById(99L);
    }

    @Test
    void getVentaUser_ShouldReturnUserVentas() {
        List<Venta> ventas = List.of(createTestVenta(1L));
        when(ventaRepository.findByUsuarioUserName("testuser")).thenReturn(ventas);

        List<VentaDTO> result = ventaService.getVentaUser("testuser");

        assertThat(result).hasSize(1);
        verify(ventaRepository).findByUsuarioUserName("testuser");
    }

    @Test
    void postVenta_ShouldReturnVentaDTO() {
        Venta venta = createTestVenta(null);
        when(ventaRepository.save(venta)).thenReturn(venta);

        VentaDTO result = ventaService.postVenta(venta);

        assertThat(result).isNotNull();
        verify(ventaRepository).save(venta);
    }

    @Test
    void delVenta_ShouldDeleteWhenExists() {
        Venta venta = createTestVenta(1L);
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        ventaService.delVenta(1L);

        verify(ventaRepository).findById(1L);
        verify(ventaRepository).delete(venta);
    }

    @Test
    void delVenta_ShouldThrowWhenNotFound() {
        when(ventaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ventaService.delVenta(99L))
                .isInstanceOf(ManejoErrores.class);
        verify(ventaRepository).findById(99L);
        verify(ventaRepository, never()).delete(any());
    }

    @Test
    void getProductoReciboId_ShouldReturnProductos() {
        Venta venta = createTestVenta(1L);
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        List<Producto> result = ventaService.getProductoReciboId(1L);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Disco A");
        assertThat(result.get(1).getNombre()).isEqualTo("Disco B");
        verify(ventaRepository).findById(1L);
    }

    @Test
    void getProductoReciboId_ShouldThrowWhenNotFound() {
        when(ventaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ventaService.getProductoReciboId(99L))
                .isInstanceOf(ManejoErrores.class);
        verify(ventaRepository).findById(99L);
    }
}
