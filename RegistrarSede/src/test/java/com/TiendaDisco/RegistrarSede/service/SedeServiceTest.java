package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.mapper.Mapper;
import com.TiendaDisco.RegistrarSede.model.Sede;
import com.TiendaDisco.RegistrarSede.repository.SedeRepository;
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
class SedeServiceTest {

    @Mock
    private SedeRepository sedeRepository;

    @InjectMocks
    private SedeService sedeService;

    private Sede sede;
    private SedeDTO sedeDTO;

    @BeforeEach
    void setUp() {
        sede = Sede.builder()
                .id(1L)
                .nombreSede("Sede Centro")
                .direccionSede("Av. Principal 123")
                .numberSedeTelefono("+56912345678")
                .build();
        sedeDTO = Mapper.toDTO(sede);
    }

    @Test
    void getAllSedes_ShouldReturnListOfSedes() {
        when(sedeRepository.findAll()).thenReturn(List.of(sede));

        List<SedeDTO> result = sedeService.getAllSedes();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreSede()).isEqualTo(sedeDTO.getNombreSede());
        verify(sedeRepository).findAll();
    }

    @Test
    void postSede_ShouldReturnSavedSede() {
        when(sedeRepository.save(any(Sede.class))).thenReturn(sede);

        Sede result = sedeService.postSede(sede);

        assertThat(result).isEqualTo(sede);
        verify(sedeRepository).save(sede);
    }

    @Test
    void getSedeId_WhenExists_ShouldReturnSede() {
        when(sedeRepository.findById(1L)).thenReturn(Optional.of(sede));

        SedeDTO result = sedeService.getSedeId(1L);

        assertThat(result.getNombreSede()).isEqualTo(sedeDTO.getNombreSede());
        verify(sedeRepository).findById(1L);
    }

    @Test
    void getSedeId_WhenNotExists_ShouldThrowException() {
        when(sedeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sedeService.getSedeId(1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id no encontrado");
        verify(sedeRepository).findById(1L);
    }

    @Test
    void putSede_WhenExists_ShouldUpdateAndReturnMessage() {
        Sede existing = Sede.builder()
                .id(1L)
                .nombreSede("Sede Centro")
                .direccionSede("Av. Principal 123")
                .numberSedeTelefono("+56912345678")
                .build();
        when(sedeRepository.findById(1L)).thenReturn(Optional.of(existing));

        Sede updatedData = Sede.builder()
                .numberSedeTelefono("+56987654321")
                .build();
        String result = sedeService.putSede(1L, updatedData);

        assertThat(result).isEqualTo("Numero modificado");
        assertThat(existing.getNumberSedeTelefono()).isEqualTo("+56987654321");
        verify(sedeRepository).findById(1L);
        verify(sedeRepository).save(existing);
    }

    @Test
    void putSede_WhenNotExists_ShouldThrowException() {
        when(sedeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sedeService.putSede(1L, sede))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Id a modificar no encontrada");
        verify(sedeRepository).findById(1L);
        verify(sedeRepository, never()).save(any());
    }

    @Test
    void deleteSedeId_WhenExists_ShouldDeleteAndReturnMessage() {
        when(sedeRepository.findById(1L)).thenReturn(Optional.of(sede));

        String result = sedeService.deleteSedeId(1L);

        assertThat(result).isEqualTo("Sede eliminada");
        verify(sedeRepository).findById(1L);
        verify(sedeRepository).delete(sede);
    }

    @Test
    void deleteSedeId_WhenNotExists_ShouldThrowException() {
        when(sedeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sedeService.deleteSedeId(1L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessage("Sede no encontrado");
        verify(sedeRepository).findById(1L);
        verify(sedeRepository, never()).delete(any());
    }
}
