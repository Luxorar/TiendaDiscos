package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void getAllProductos_returnsAllProductos() {
        Producto producto = new Producto();
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> result = productoService.getAllProductos();

        assertThat(result).hasSize(1).contains(producto);
        verify(productoRepository).findAll();
    }

    @Test
    void getListaProducto_whenFound_returnsProductos() {
        Producto producto = new Producto();
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(producto);
        Carrito carrito = new Carrito();
        carrito.setProductosAgregados(productos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        ArrayList<Producto> result = productoService.getListaProducto(1L, null);

        assertThat(result).hasSize(1).contains(producto);
    }

    @Test
    void getListaProducto_whenCarritoNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.getListaProducto(999L, null))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }

    @Test
    void getProducto_whenFound_returnsProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(producto);
        Carrito carrito = new Carrito();
        carrito.setProductosAgregados(productos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        Producto result = productoService.getProducto(1L, 1L);

        assertThat(result).isSameAs(producto);
    }

    @Test
    void getProducto_whenCarritoNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.getProducto(999L, 1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }

    @Test
    void getProducto_whenProductoNotFound_throwsManejoErrores() {
        Carrito carrito = new Carrito();
        carrito.setProductosAgregados(new ArrayList<>());
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        assertThatThrownBy(() -> productoService.getProducto(1L, 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Producto no encontrado");
    }

    @Test
    void postProducto_savesAndAddsToCarrito() {
        Carrito carrito = new Carrito();
        carrito.setProductosAgregados(new ArrayList<>());
        Producto newProducto = new Producto();
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(productoRepository.save(newProducto)).thenReturn(newProducto);

        Producto result = productoService.postProducto(1L, 1L, newProducto);

        assertThat(result).isSameAs(newProducto);
        assertThat(carrito.getProductosAgregados()).contains(newProducto);
        verify(carritoRepository).save(carrito);
    }

    @Test
    void putProducto_updatesExistingProducto() {
        Producto existente = new Producto();
        existente.setId(1L);
        existente.setNombreProducto("old");
        existente.setPrecio(100);

        Producto update = new Producto();
        update.setId(1L);
        update.setNombreProducto("new");
        update.setPrecio(200);

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(existente);
        Carrito carrito = new Carrito();
        carrito.setProductosAgregados(productos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(productoRepository.save(existente)).thenReturn(existente);

        Producto result = productoService.putProducto(1L, update);

        assertThat(result.getNombreProducto()).isEqualTo("new");
        assertThat(result.getPrecio()).isEqualTo(200);
        verify(productoRepository).save(existente);
    }

    @Test
    void deleteProducto_removesAndDeletes() {
        Producto producto = new Producto();
        producto.setId(1L);
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(producto);
        Carrito carrito = new Carrito();
        carrito.setProductosAgregados(productos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        String result = productoService.deleteProducto(1L, 1L);

        assertThat(result).isEqualTo("Producto eliminado del carrito");
        assertThat(carrito.getProductosAgregados()).doesNotContain(producto);
        verify(carritoRepository).save(carrito);
        verify(productoRepository).delete(producto);
    }

    @Test
    void deleteProducto_whenCarritoNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.deleteProducto(999L, 1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }
}
