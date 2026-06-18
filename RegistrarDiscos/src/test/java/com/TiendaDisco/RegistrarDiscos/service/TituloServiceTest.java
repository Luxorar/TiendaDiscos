package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.repository.TituloRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TituloServiceTest {

    @Mock
    private TituloRepository tituloRepository;

    @InjectMocks
    private TituloService tituloService;

    @Test
    void debeRetornarTodosLosTitulos() {
        Titulo titulo1 = new Titulo(1L, "Rock Clasico", null);
        Titulo titulo2 = new Titulo(2L, "Pop Latino", null);

        when(tituloRepository.findAll()).thenReturn(List.of(titulo1, titulo2));

        List<Titulo> resultado = tituloService.getAllTitulos();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getTitulo()).isEqualTo("Rock Clasico");
        assertThat(resultado.get(1).getTitulo()).isEqualTo("Pop Latino");
        verify(tituloRepository, times(1)).findAll();
    }

    @Test
    void debeRetornarTituloCuandoExiste() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);

        when(tituloRepository.findById(1L)).thenReturn(Optional.of(titulo));

        Titulo resultado = tituloService.getTituloId(1L);

        assertThat(resultado.getTitulo()).isEqualTo("Rock Clasico");
        verify(tituloRepository, times(1)).findById(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoTituloNoExiste() {
        when(tituloRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tituloService.getTituloId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id no encontrado");
    }

    @Test
    void debeGuardarTitulo() {
        Titulo titulo = new Titulo(null, "Rock Clasico", null);

        when(tituloRepository.save(any(Titulo.class))).thenReturn(titulo);

        Titulo resultado = tituloService.postTitulo(titulo);

        assertThat(resultado.getTitulo()).isEqualTo("Rock Clasico");
        verify(tituloRepository).save(any(Titulo.class));
    }

    @Test
    void debeModificarTitulo() {
        Titulo tituloExistente = new Titulo(1L, "Rock Clasico", null);
        Titulo nuevosDatos = new Titulo(null, "Rock Moderno", null);

        when(tituloRepository.findById(1L)).thenReturn(Optional.of(tituloExistente));

        String resultado = tituloService.putTitulo(1L, nuevosDatos);

        assertThat(resultado).isEqualTo("Titulo modificado");
        assertThat(tituloExistente.getTitulo()).isEqualTo("Rock Moderno");
        verify(tituloRepository, times(1)).save(tituloExistente);
    }

    @Test
    void debeLanzarExcepcionCuandoIdModificacionNoExiste() {
        when(tituloRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tituloService.putTitulo(99L, new Titulo()))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id a modificar no encontrado");
    }

    @Test
    void debeEliminarTitulo() {
        Titulo titulo = new Titulo(1L, "Rock Clasico", null);

        when(tituloRepository.findById(1L)).thenReturn(Optional.of(titulo));

        String resultado = tituloService.deleteTitulo(1L);

        assertThat(resultado).isEqualTo("Titulo eliminado");
        verify(tituloRepository, times(1)).delete(titulo);
    }

    @Test
    void debeLanzarExcepcionCuandoIdEliminacionNoExiste() {
        when(tituloRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tituloService.deleteTitulo(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id a eliminar no encontrado");
    }
}
