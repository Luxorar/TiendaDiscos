package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.Repository.DescuentoRepository;
import com.TiendaDisco.AdministracionDescuentos.exception.ManejoErrores;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Estado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DescuentoServiceTest {

    @Mock
    private DescuentoRepository descuentoRepository;

    @InjectMocks
    private DescuentoService descuentoService;

    //-----------------------obtener todos los descuentos------------------------
    @Test
    void debeRetornarTodosLosDescuentos() {
        Descuento descuentoVerano = new Descuento(1L, "Descuento Verano 2026", null,
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 15.0
        );
        Descuento descuentoLanzamiento = new Descuento(2L, "Lanzamiento Nuevo",
                null, Estado.INACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );

        when(descuentoRepository.findAll()).thenReturn(List.of(descuentoVerano, descuentoLanzamiento));

        List<DescuentoDTO> resultado = descuentoService.getAllDescuentos();

        //asserts
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Descuento Verano 2026");
        assertThat(resultado.get(0).getDescuento()).isEqualTo(15.0);
        assertThat(resultado.get(1).getNombre()).isEqualTo("Lanzamiento Nuevo");
        assertThat(resultado.get(1).getDescuento()).isEqualTo(10.5);
        verify(descuentoRepository, times(1)).findAll();
    }
    //-----------------------obtener x id------------------------
    @Test
    void debeRetornarDescuentoCuandoExiste() {
        // Arrange: simulamos que el repo devuelve un usuario
        Descuento descuentoLanzamiento = new Descuento(1L, "Lanzamiento Nuevo",
                null, Estado.INACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );
        when(descuentoRepository.findById(1L)).thenReturn(Optional.of(descuentoLanzamiento));

        // Act
        DescuentoDTO resultado = descuentoService.getDescuentoId(1L);

        // Assert
        assertThat(resultado.getNombre()).isEqualTo("Lanzamiento Nuevo");
        assertThat(resultado.getEstado()).isEqualTo(Estado.INACTIVO);
        assertThat(resultado.getDescuento()).isEqualTo(10.5);
        verify(descuentoRepository, times(1)).findById(1L);
    }
    @Test
    void debeLanzarExcepcionCuandoNoExiste() {
        // Arrange: el repo no encuentra nada
        when(descuentoRepository.findById(99L)).thenReturn(Optional.empty());

        // Assert: el service debe lanzar ManejoErrores
        assertThatThrownBy(() -> descuentoService.getDescuentoId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("99");
    }

    //---------------------------creación---------------------------------------------------------------

    @Test
    void debeGuardarDescuento() {
        Descuento descuentoLanzamiento = new Descuento(1L, "Lanzamiento Nuevo",
                null, Estado.INACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );
        when(descuentoRepository.save(any(Descuento.class))).thenReturn(descuentoLanzamiento);

        Descuento resultado = descuentoService.postDescuento(descuentoLanzamiento);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Lanzamiento Nuevo");
        verify(descuentoRepository).save(any(Descuento.class));
    }

    //-----------------------obtener x nombre-----------------------------------------------------------
    @Test
    void debeRetornarDescuentoxNombreCuandoExiste() {
        Descuento descuento = new Descuento(1L, "Lanzamiento Nuevo",
                null, Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );
        when(descuentoRepository.findByNombre("Lanzamiento Nuevo")).thenReturn(Optional.of(descuento));

        Descuento resultado = descuentoService.getDescuentoNombre("Lanzamiento Nuevo");

        assertThat(resultado.getNombre()).isEqualTo("Lanzamiento Nuevo");
        assertThat(resultado.getEstado()).isEqualTo(Estado.ACTIVO);
        assertThat(resultado.getDescuento()).isEqualTo(10.5);
        verify(descuentoRepository, times(1)).findByNombre("Lanzamiento Nuevo");
    }

    @Test
    void debeLanzarExcepcionCuandoNombreNoExiste() {
        when(descuentoRepository.findByNombre("No existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> descuentoService.getDescuentoNombre("No existe"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No existe");
    }

    //---------------------------modificación-----------------------------------------------------------
    @Test
    void debeModificarDescuento() {
        Descuento descuentoExistente = new Descuento(1L, "Original",
                null, Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        Descuento nuevosDatos = new Descuento(null, "Modificado",
                null, Estado.INACTIVO, null, null, 20.0
        );
        when(descuentoRepository.findById(1L)).thenReturn(Optional.of(descuentoExistente));

        String resultado = descuentoService.putDescuento(1L, nuevosDatos);

        assertThat(resultado).isEqualTo("Descuento modificado exitosamente");
        assertThat(descuentoExistente.getNombre()).isEqualTo("Modificado");
        assertThat(descuentoExistente.getEstado()).isEqualTo(Estado.INACTIVO);
        assertThat(descuentoExistente.getDescuento()).isEqualTo(20.0);
        verify(descuentoRepository, times(1)).save(descuentoExistente);
    }

    @Test
    void debeLanzarExcepcionCuandoIdModificacionNoExiste() {
        when(descuentoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> descuentoService.putDescuento(99L, new Descuento()))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id de descuento a modificar no encontrado");
    }

    //---------------------------eliminación-----------------------------------------------------------
    @Test
    void debeEliminarDescuento() {
        Descuento descuento = new Descuento(1L, "Eliminar",
                null, Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 15.0
        );
        when(descuentoRepository.findById(1L)).thenReturn(Optional.of(descuento));

        String resultado = descuentoService.deleteDescuento(1L);

        assertThat(resultado).isEqualTo("Descuento eliminado exitosamente");
        verify(descuentoRepository, times(1)).delete(descuento);
    }

    @Test
    void debeLanzarExcepcionCuandoIdEliminacionNoExiste() {
        when(descuentoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> descuentoService.deleteDescuento(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Id de descuento a eliminar no encontrado");
    }

}
