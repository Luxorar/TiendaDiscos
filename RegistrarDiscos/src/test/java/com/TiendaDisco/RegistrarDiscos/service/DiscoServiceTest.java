package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.repository.DiscoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscoServiceTest {

    @Mock
    private DiscoRepository discoRepository;

    @InjectMocks
    private DiscoService discoService;

    @Test
    void debeRetornarTodosLosDiscos() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);
        Disco disco1 = new Disco(1L, "Back in Black", "AC/DC", 15000, List.of(titulo));
        Disco disco2 = new Disco(2L, "The Wall", "Pink Floyd", 20000, Collections.emptyList());

        when(discoRepository.findAll()).thenReturn(List.of(disco1, disco2));

        List<DiscoDTO> resultado = discoService.getAllDiscos();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getNombreDisco()).isEqualTo("Back in Black");
        assertThat(resultado.get(0).getArtista()).isEqualTo("AC/DC");
        assertThat(resultado.get(0).getTitulos()).isEqualTo(List.of("Rock Clasico"));
        assertThat(resultado.get(1).getNombreDisco()).isEqualTo("The Wall");
        assertThat(resultado.get(1).getArtista()).isEqualTo("Pink Floyd");
        assertThat(resultado.get(1).getTitulos()).isEqualTo(Collections.emptyList());
        verify(discoRepository, times(1)).findAll();
    }

    @Test
    void debeRetornarDiscoCuandoExiste() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);
        Disco disco = new Disco(1L, "Back in Black", "AC/DC", 15000, List.of(titulo));

        when(discoRepository.findById(1L)).thenReturn(Optional.of(disco));

        DiscoDTO resultado = discoService.getDiscoId(1L);

        assertThat(resultado.getNombreDisco()).isEqualTo("Back in Black");
        assertThat(resultado.getArtista()).isEqualTo("AC/DC");
        assertThat(resultado.getPrecio()).isEqualTo(15000);
        assertThat(resultado.getTitulos()).isEqualTo(List.of("Rock Clasico"));
        verify(discoRepository, times(1)).findById(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoNoExiste() {
        when(discoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.getDiscoId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id no encontrado");
    }

    @Test
    void debeGuardarDisco() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);
        Disco disco = new Disco(null, "Back in Black", "AC/DC", 15000, List.of(titulo));

        when(discoRepository.save(any(Disco.class))).thenReturn(disco);

        Disco resultado = discoService.postDisco(disco);

        assertThat(resultado.getNombreDisco()).isEqualTo("Back in Black");
        assertThat(resultado.getArtista()).isEqualTo("AC/DC");
        verify(discoRepository).save(any(Disco.class));
    }

    @Test
    void debeModificarDisco() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);
        Disco discoExistente = new Disco(1L, "Back in Black", "AC/DC", 15000, List.of(titulo));
        Disco nuevosDatos = new Disco(null, "Highway to Hell", "AC/DC", 18000, List.of(titulo));

        when(discoRepository.findById(1L)).thenReturn(Optional.of(discoExistente));

        String resultado = discoService.putDisco(1L, nuevosDatos);

        assertThat(resultado).isEqualTo("Disco modificado");
        assertThat(discoExistente.getNombreDisco()).isEqualTo("Highway to Hell");
        assertThat(discoExistente.getPrecio()).isEqualTo(18000);
        verify(discoRepository, times(1)).save(discoExistente);
    }

    @Test
    void debeLanzarExcepcionCuandoIdModificacionNoExiste() {
        when(discoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.putDisco(99L, new Disco()))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id a modificar no encontrado");
    }

    @Test
    void debeEliminarDisco() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);
        Disco disco = new Disco(1L, "Back in Black", "AC/DC", 15000, List.of(titulo));

        when(discoRepository.findById(1L)).thenReturn(Optional.of(disco));

        String resultado = discoService.deleteDisco(1L);

        assertThat(resultado).isEqualTo("Disco eliminado");
        verify(discoRepository, times(1)).delete(disco);
    }

    @Test
    void debeLanzarExcepcionCuandoIdEliminacionNoExiste() {
        when(discoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> discoService.deleteDisco(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id a eliminar no encontrado");
    }
}
