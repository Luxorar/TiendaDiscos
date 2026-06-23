package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.mapper.Mapper;
import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.repository.DiscoRepository;
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
class DiscoServiceTest {

    @Mock
    private DiscoRepository discoRepository;

    @InjectMocks
    private DiscoService discoService;

    private Disco disco;
    private DiscoDTO discoDTO;

    @BeforeEach
    void setUp() {
        disco = Disco.builder()
                .id(1L)
                .nombreDisco("Thriller")
                .artista("Michael Jackson")
                .precio(15000)
                .build();
        discoDTO = Mapper.toDTO(disco);
    }

    @Test
    void getAllDiscos_ShouldReturnListOfDiscos() {
        when(discoRepository.findAll()).thenReturn(List.of(disco));

        List<DiscoDTO> result = discoService.getAllDiscos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreDisco()).isEqualTo(discoDTO.getNombreDisco());
        verify(discoRepository).findAll();
    }

    @Test
    void postDisco_ShouldReturnSavedDisco() {
        when(discoRepository.save(any(Disco.class))).thenReturn(disco);

        Disco result = discoService.postDisco(disco);

        assertThat(result).isEqualTo(disco);
        verify(discoRepository).save(disco);
    }

    @Test
    void getDiscoId_WhenExists_ShouldReturnDisco() {
        when(discoRepository.findById(1L)).thenReturn(Optional.of(disco));

        DiscoDTO result = discoService.getDiscoId(1L);

        assertThat(result.getNombreDisco()).isEqualTo(discoDTO.getNombreDisco());
        verify(discoRepository).findById(1L);
    }

    @Test
    void getDiscoId_WhenNotExists_ShouldThrowException() {
        when(discoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.getDiscoId(1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id no encontrada");
        verify(discoRepository).findById(1L);
    }

    @Test
    void putDisco_WhenExists_ShouldUpdateAndReturnMessage() {
        Disco existing = Disco.builder()
                .id(1L)
                .nombreDisco("Thriller")
                .artista("Michael Jackson")
                .precio(15000)
                .build();
        when(discoRepository.findById(1L)).thenReturn(Optional.of(existing));

        Disco updatedData = Disco.builder()
                .nombreDisco("Bad")
                .artista("Michael Jackson")
                .build();
        String result = discoService.putDisco(1L, updatedData);

        assertThat(result).isEqualTo("Datos del disco modificados");
        assertThat(existing.getNombreDisco()).isEqualTo("Bad");
        assertThat(existing.getArtista()).isEqualTo("Michael Jackson");
        verify(discoRepository).findById(1L);
    }

    @Test
    void putDisco_WhenNotExists_ShouldThrowException() {
        when(discoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.putDisco(1L, disco))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id a modificar no encontrada");
        verify(discoRepository).findById(1L);
        verify(discoRepository, never()).save(any());
    }

    @Test
    void deleteDisco_WhenExists_ShouldDeleteAndReturnMessage() {
        when(discoRepository.findById(1L)).thenReturn(Optional.of(disco));

        String result = discoService.deleteDisco(1L);

        assertThat(result).isEqualTo("Disco elimiando");
        verify(discoRepository).findById(1L);
        verify(discoRepository).delete(disco);
    }

    @Test
    void deleteDisco_WhenNotExists_ShouldThrowException() {
        when(discoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.deleteDisco(1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Usuario no encontrado");
        verify(discoRepository).findById(1L);
        verify(discoRepository, never()).delete(any());
    }
}
