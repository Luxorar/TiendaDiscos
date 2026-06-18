package com.TiendaDisco.RegistrarProductos.service;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarProductos.model.Producto;
import com.TiendaDisco.RegistrarProductos.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService service;

    private Producto createProducto(Long id, String nombre, String marca, Integer precio) {
        return Producto.builder()
                .id(id)
                .nombreProducto(nombre)
                .marca(marca)
                .precio(precio)
                .build();
    }

    @Test
    void getAllProductos_ShouldReturnListOfProductoDTO() {
        Producto p1 = createProducto(1L, "Producto1", "Marca1", 100);
        Producto p2 = createProducto(2L, "Producto2", "Marca2", 200);
        when(repo.findAll()).thenReturn(List.of(p1, p2));

        List<ProductoDTO> result = service.getAllProductos();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Producto1");
        assertThat(result.get(0).getMarca()).isEqualTo("Marca1");
        assertThat(result.get(0).getPrecio()).isEqualTo(100);
    }

    @Test
    void getProductoID_WhenProductoExists_ShouldReturnProductoDTO() {
        Producto p = createProducto(1L, "Test", "Sony", 500);
        when(repo.findById(1L)).thenReturn(Optional.of(p));

        ProductoDTO result = service.getProductoID(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNombreProducto()).isEqualTo("Test");
        assertThat(result.getMarca()).isEqualTo("Sony");
        assertThat(result.getPrecio()).isEqualTo(500);
    }

    @Test
    void getProductoID_WhenProductoNotFound_ShouldThrowManejoErrores() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ManejoErrores.class, () -> service.getProductoID(99L));
    }

    @Test
    void getProductoNombre_ShouldReturnListOfProductoDTO() {
        Producto p = createProducto(1L, "Test", "Sony", 300);
        when(repo.findByNombreProducto("Test")).thenReturn(List.of(p));

        List<ProductoDTO> result = service.getProductoNombre("Test");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Test");
        assertThat(result.get(0).getMarca()).isEqualTo("Sony");
    }

    @Test
    void getProductoMarca_ShouldReturnListOfProductoDTO() {
        Producto p = createProducto(1L, "Consola", "Sony", 400);
        when(repo.findByMarca("Sony")).thenReturn(List.of(p));

        List<ProductoDTO> result = service.getProductoMarca("Sony");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMarca()).isEqualTo("Sony");
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Consola");
    }

    @Test
    void postProducto_ShouldReturnEntity() {
        Producto p = createProducto(null, "Nuevo", "Marca", 150);
        Producto saved = createProducto(1L, "Nuevo", "Marca", 150);
        when(repo.save(p)).thenReturn(saved);

        Producto result = service.postProducto(p);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNombreProducto()).isEqualTo("Nuevo");
    }

    @Test
    void deleteProducto_WhenProductoExists_ShouldDeleteAndReturnMessage() {
        Producto p = createProducto(1L, "Borrar", "Marca", 100);
        when(repo.findById(1L)).thenReturn(Optional.of(p));

        String result = service.deleteProducto(1L);

        assertThat(result).isEqualTo("Producto eliminado");
        verify(repo).delete(p);
    }

    @Test
    void deleteProducto_WhenProductoNotFound_ShouldThrowManejoErrores() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ManejoErrores.class, () -> service.deleteProducto(99L));
        verify(repo, never()).delete(any());
    }
}
