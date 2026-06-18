package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarritoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoService carritoService;

    @Test
    void getListaCarrito_returnsAllCarritos() {
        when(carritoRepository.findAll()).thenReturn(List.of());

        List<CarritoDTO> result = carritoService.getListaCarrito();

        assertThat(result).isEmpty();
        verify(carritoRepository).findAll();
    }

    @Test
    void postCarrito_savesAndReturnsCarrito() {
        Carrito carrito = new Carrito();
        when(carritoRepository.save(carrito)).thenReturn(carrito);

        Carrito result = carritoService.postCarrito(carrito);

        assertThat(result).isSameAs(carrito);
        verify(carritoRepository).save(carrito);
    }

    @Test
    void getCarrito_whenFound_returnsDTO() {
        Carrito carrito = new Carrito();
        com.TiendaDisco.CarritoCompras.model.User user = new com.TiendaDisco.CarritoCompras.model.User();
        user.setUserName("testuser");
        carrito.setUser(user);
        when(carritoRepository.findByUserUserName("testuser")).thenReturn(Optional.of(carrito));

        CarritoDTO result = carritoService.getCarrito("testuser");

        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo("testuser");
        verify(carritoRepository).findByUserUserName("testuser");
    }

    @Test
    void getCarrito_whenNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserUserName("unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carritoService.getCarrito("unknown"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Usuario no encontrado");
        verify(carritoRepository).findByUserUserName("unknown");
    }

    @Test
    void updateCarrito_whenFound_updatesAndReturnsMessage() {
        Carrito existing = new Carrito();
        existing.setDescuento(0.0);
        Carrito update = new Carrito();
        update.setDescuento(10.0);
        when(carritoRepository.findByUserUserName("testuser")).thenReturn(Optional.of(existing));

        String result = carritoService.updateCarrito(update, "testuser");

        assertThat(result).isEqualTo("Carrito actualizado");
        assertThat(existing.getDescuento()).isEqualTo(10.0);
        verify(carritoRepository).save(existing);
    }

    @Test
    void updateCarrito_whenNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserUserName("unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carritoService.updateCarrito(new Carrito(), "unknown"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
        verify(carritoRepository).findByUserUserName("unknown");
        verify(carritoRepository, never()).save(any());
    }

    @Test
    void deleteCarrito_whenFound_deletesCarrito() {
        Carrito carrito = new Carrito();
        when(carritoRepository.findByUserUserName("testuser")).thenReturn(Optional.of(carrito));

        carritoService.deleteCarrito("testuser");

        verify(carritoRepository).delete(carrito);
    }

    @Test
    void deleteCarrito_whenNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserUserName("unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carritoService.deleteCarrito("unknown"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
        verify(carritoRepository).findByUserUserName("unknown");
        verify(carritoRepository, never()).delete(any());
    }
}
