package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id(1L)
                .nombreProducto("Vinilo")
                .precio(5000)
                .build();
    }

    @Test
    void getAllProductos_ShouldReturnListOfProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> result = productoService.getAllProductos();

        assertThat(result).hasSize(1).contains(producto);
        verify(productoRepository).findAll();
    }

    @Test
    void postProducto_ShouldReturnSavedProducto() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto result = productoService.postProducto(producto);

        assertThat(result).isEqualTo(producto);
        verify(productoRepository).save(producto);
    }

    @Test
    void getProductoId_WhenExists_ShouldReturnProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoService.getProductoId(1L);

        assertThat(result).isEqualTo(producto);
        verify(productoRepository).findById(1L);
    }

    @Test
    void getProductoId_WhenNotExists_ShouldThrowException() {
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.getProductoId(1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id no encontrada");
        verify(productoRepository).findById(1L);
    }

    @Test
    void putProducto_WhenExists_ShouldUpdateAndReturnMessage() {
        Producto existing = Producto.builder()
                .id(1L)
                .nombreProducto("Vinilo")
                .precio(5000)
                .build();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(existing));

        Producto updatedData = Producto.builder()
                .nombreProducto("CD")
                .precio(3000)
                .build();
        String result = productoService.putProducto(1L, updatedData);

        assertThat(result).isEqualTo("Datos del Producto modificados");
        assertThat(existing.getNombreProducto()).isEqualTo("CD");
        assertThat(existing.getPrecio()).isEqualTo(3000);
        verify(productoRepository).findById(1L);
    }

    @Test
    void putProducto_WhenNotExists_ShouldThrowException() {
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.putProducto(1L, producto))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id a modificar no encontrada");
        verify(productoRepository).findById(1L);
        verify(productoRepository, never()).save(any());
    }

    @Test
    void deleteProducto_WhenExists_ShouldDeleteAndReturnMessage() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        String result = productoService.deleteProducto(1L);

        assertThat(result).isEqualTo("Producto elimiando");
        verify(productoRepository).findById(1L);
        verify(productoRepository).delete(producto);
    }

    @Test
    void deleteProducto_WhenNotExists_ShouldThrowException() {
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.deleteProducto(1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Usuario no encontrado");
        verify(productoRepository).findById(1L);
        verify(productoRepository, never()).delete(any());
    }
}
