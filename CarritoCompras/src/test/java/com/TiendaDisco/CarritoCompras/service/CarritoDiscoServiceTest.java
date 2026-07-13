package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.client.DiscoClient;
import com.TiendaDisco.CarritoCompras.dto.CarritoDiscoDTO;
import com.TiendaDisco.CarritoCompras.dto.DiscoDTO;
import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.CarritoDisco;
import com.TiendaDisco.CarritoCompras.repository.CarritoDiscoRepository;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarritoDiscoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private CarritoDiscoRepository carritoDiscoRepository;

    @Mock
    private DiscoClient discoClient;

    @InjectMocks
    private CarritoDiscoService carritoDiscoService;

    private DiscoDTO mockDiscoDTO() {
        return DiscoDTO.builder()
                .id(1L).nombreDisco("Thriller").artista("Michael Jackson").precio(40000).imagen(null)
                .build();
    }

    @Test
    void getListaDiscos_returnsDiscoDTOs() {
        Carrito carrito = new Carrito();
        CarritoDisco cd = CarritoDisco.builder().id(1L).discoId(1L).qty(2).carrito(carrito).build();
        carrito.setDiscosAgregados(new ArrayList<>(List.of(cd)));
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(mockDiscoDTO()));

        List<CarritoDiscoDTO> result = carritoDiscoService.getListaDiscos(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreDisco()).isEqualTo("Thriller");
        assertThat(result.get(0).getQty()).isEqualTo(2);
    }

    @Test
    void getListaDiscos_whenCarritoNotFound_throws() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> carritoDiscoService.getListaDiscos(999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }

    @Test
    void addDisco_newDisco_createsEntry() {
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(new ArrayList<>());
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(1L, 1L)).thenReturn(Optional.empty());
        when(carritoDiscoRepository.save(any())).thenAnswer(inv -> {
            CarritoDisco cd = inv.getArgument(0);
            cd.setId(1L);
            return cd;
        });
        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(mockDiscoDTO()));

        CarritoDiscoDTO result = carritoDiscoService.addDisco(1L, 1L);

        assertThat(result.getQty()).isEqualTo(1);
        assertThat(result.getNombreDisco()).isEqualTo("Thriller");
        verify(carritoDiscoRepository).save(any());
    }

    @Test
    void addDisco_existingDisco_incrementsQty() {
        Carrito carrito = new Carrito();
        CarritoDisco existing = CarritoDisco.builder().id(1L).discoId(1L).qty(1).carrito(carrito).build();
        carrito.setDiscosAgregados(new ArrayList<>(List.of(existing)));
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(1L, 1L)).thenReturn(Optional.of(existing));
        when(carritoDiscoRepository.save(existing)).thenReturn(existing);
        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(mockDiscoDTO()));

        CarritoDiscoDTO result = carritoDiscoService.addDisco(1L, 1L);

        assertThat(result.getQty()).isEqualTo(2);
    }

    @Test
    void updateQty_setsNewQuantity() {
        CarritoDisco cd = CarritoDisco.builder().id(1L).discoId(1L).qty(1).build();
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(1L, 1L)).thenReturn(Optional.of(cd));
        when(carritoDiscoRepository.save(cd)).thenReturn(cd);
        when(discoClient.obtenerDiscoPorId(1L)).thenReturn(ResponseEntity.ok(mockDiscoDTO()));

        CarritoDiscoDTO result = carritoDiscoService.updateQty(1L, 1L, 5);

        assertThat(result.getQty()).isEqualTo(5);
    }

    @Test
    void updateQty_zeroQty_deletesEntry() {
        CarritoDisco cd = CarritoDisco.builder().id(1L).discoId(1L).qty(1).build();
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(1L, 1L)).thenReturn(Optional.of(cd));

        CarritoDiscoDTO result = carritoDiscoService.updateQty(1L, 1L, 0);

        assertThat(result).isNull();
        verify(carritoDiscoRepository).delete(cd);
    }

    @Test
    void deleteDisco_highQty_decrements() {
        CarritoDisco cd = CarritoDisco.builder().id(1L).discoId(1L).qty(3).build();
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(1L, 1L)).thenReturn(Optional.of(cd));
        when(carritoDiscoRepository.save(cd)).thenReturn(cd);

        String result = carritoDiscoService.deleteDisco(1L, 1L);

        assertThat(result).isEqualTo("Cantidad decrementada");
        assertThat(cd.getQty()).isEqualTo(2);
    }

    @Test
    void deleteDisco_qtyOne_deletesEntry() {
        CarritoDisco cd = CarritoDisco.builder().id(1L).discoId(1L).qty(1).build();
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(1L, 1L)).thenReturn(Optional.of(cd));

        String result = carritoDiscoService.deleteDisco(1L, 1L);

        assertThat(result).isEqualTo("Disco eliminado del carrito");
        verify(carritoDiscoRepository).delete(cd);
    }

    @Test
    void deleteDisco_whenNotFound_throws() {
        when(carritoDiscoRepository.findByCarritoUserIdAndDiscoId(999L, 1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> carritoDiscoService.deleteDisco(999L, 1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Disco no encontrado");
    }
}
