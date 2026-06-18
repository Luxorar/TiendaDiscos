package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.repository.ResenaRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void getAllResenas_shouldReturnAllResenas() {
        Resena r1 = Resena.builder().id(1L).mensaje("Mensaje 1").build();
        Resena r2 = Resena.builder().id(2L).mensaje("Mensaje 2").build();
        when(resenaRepository.findAll()).thenReturn(List.of(r1, r2));

        List<Resena> result = resenaService.getAllResenas();

        assertThat(result).hasSize(2).containsExactly(r1, r2);
        verify(resenaRepository).findAll();
    }

    @Test
    void postResena_shouldSaveAndReturnResena() {
        Resena resena = Resena.builder().mensaje("Nueva reseña").build();
        Resena saved = Resena.builder().id(1L).mensaje("Nueva reseña").build();
        when(resenaRepository.save(any(Resena.class))).thenReturn(saved);

        Resena result = resenaService.postResena(resena);

        assertThat(result).isEqualTo(saved);
        assertThat(result.getId()).isEqualTo(1L);
        verify(resenaRepository).save(resena);
    }

    @Test
    void getResenaId_whenExists_shouldReturnResena() {
        Resena resena = Resena.builder().id(1L).mensaje("Reseña existente").build();
        when(resenaRepository.findById(1L)).thenReturn(Optional.of(resena));

        Resena result = resenaService.getResenaId(1L);

        assertThat(result).isEqualTo(resena);
        verify(resenaRepository).findById(1L);
    }

    @Test
    void getResenaId_whenNotExists_shouldThrowException() {
        when(resenaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> resenaService.getResenaId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id no encontrada");
        verify(resenaRepository).findById(99L);
    }

    @Test
    void deleteResena_whenExists_shouldDeleteAndReturnMessage() {
        Resena resena = Resena.builder().id(1L).mensaje("A eliminar").build();
        when(resenaRepository.findById(1L)).thenReturn(Optional.of(resena));

        String result = resenaService.deleteResena(1L);

        assertThat(result).isEqualTo("Reseña eliminada");
        verify(resenaRepository).delete(resena);
    }

    @Test
    void deleteResena_whenNotExists_shouldThrowException() {
        when(resenaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> resenaService.deleteResena(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Reseña no encontrada");
        verify(resenaRepository, never()).delete(any());
    }
}
