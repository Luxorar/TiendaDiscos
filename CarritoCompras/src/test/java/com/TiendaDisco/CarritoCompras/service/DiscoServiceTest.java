package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.repository.DiscoRepository;
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
class DiscoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private DiscoRepository discoRepository;

    @InjectMocks
    private DiscoService discoService;

    @Test
    void getAllDiscos_returnsAllDiscos() {
        Disco disco = new Disco();
        when(discoRepository.findAll()).thenReturn(List.of(disco));

        List<Disco> result = discoService.getAllDiscos();

        assertThat(result).hasSize(1).contains(disco);
        verify(discoRepository).findAll();
    }

    @Test
    void getListaDiscos_whenFound_returnsDiscos() {
        Disco disco = new Disco();
        ArrayList<Disco> discos = new ArrayList<>();
        discos.add(disco);
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(discos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        List<Disco> result = discoService.getListaDiscos(1L);

        assertThat(result).hasSize(1).contains(disco);
    }

    @Test
    void getListaDiscos_whenCarritoNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.getListaDiscos(999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }

    @Test
    void getDisco_whenFound_returnsDisco() {
        Disco disco = new Disco();
        disco.setId(1L);
        ArrayList<Disco> discos = new ArrayList<>();
        discos.add(disco);
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(discos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        Disco result = discoService.getDisco(1L, 1L);

        assertThat(result).isSameAs(disco);
    }

    @Test
    void getDisco_whenCarritoNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.getDisco(999L, 1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }

    @Test
    void getDisco_whenDiscoNotFound_throwsManejoErrores() {
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(new ArrayList<>());
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        assertThatThrownBy(() -> discoService.getDisco(1L, 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Disco no encontrado");
    }

    @Test
    void postDisco_savesAndAddsToCarrito() {
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(new ArrayList<>());
        Disco newDisco = new Disco();
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(discoRepository.save(newDisco)).thenReturn(newDisco);

        Disco result = discoService.postDisco(1L, 1L, newDisco);

        assertThat(result).isSameAs(newDisco);
        assertThat(carrito.getDiscosAgregados()).contains(newDisco);
        verify(carritoRepository).save(carrito);
    }

    @Test
    void putDisco_updatesExistingDisco() {
        Disco existente = new Disco();
        existente.setId(1L);
        existente.setNombreDisco("old");
        existente.setArtista("old");
        existente.setPrecio(100);

        Disco update = new Disco();
        update.setId(1L);
        update.setNombreDisco("new");
        update.setArtista("new");
        update.setPrecio(200);

        ArrayList<Disco> discos = new ArrayList<>();
        discos.add(existente);
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(discos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));
        when(discoRepository.save(existente)).thenReturn(existente);

        Disco result = discoService.putDisco(1L, update);

        assertThat(result.getNombreDisco()).isEqualTo("new");
        assertThat(result.getArtista()).isEqualTo("new");
        assertThat(result.getPrecio()).isEqualTo(200);
        verify(discoRepository).save(existente);
    }

    @Test
    void deleteDiscos_removesAndDeletes() {
        Disco disco = new Disco();
        disco.setId(1L);
        ArrayList<Disco> discos = new ArrayList<>();
        discos.add(disco);
        Carrito carrito = new Carrito();
        carrito.setDiscosAgregados(discos);
        when(carritoRepository.findByUserId(1L)).thenReturn(Optional.of(carrito));

        String result = discoService.deleteDiscos(1L, 1L);

        assertThat(result).isEqualTo("Disco eliminado del carrito");
        assertThat(carrito.getDiscosAgregados()).doesNotContain(disco);
        verify(carritoRepository).save(carrito);
        verify(discoRepository).delete(disco);
    }

    @Test
    void deleteDiscos_whenCarritoNotFound_throwsManejoErrores() {
        when(carritoRepository.findByUserId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.deleteDiscos(999L, 1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Carrito no encontrado");
    }
}
