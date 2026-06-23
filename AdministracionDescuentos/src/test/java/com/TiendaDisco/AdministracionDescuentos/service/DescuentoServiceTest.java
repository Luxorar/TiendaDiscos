package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.DTO.DiscoDTO;
import com.TiendaDisco.AdministracionDescuentos.DTO.ProductoDTO;
import com.TiendaDisco.AdministracionDescuentos.Repository.DescuentoRepository;
import com.TiendaDisco.AdministracionDescuentos.client.DiscoClient;
import com.TiendaDisco.AdministracionDescuentos.client.ProductoClient;
import com.TiendaDisco.AdministracionDescuentos.exception.ManejoErrores;
import com.TiendaDisco.AdministracionDescuentos.mapper.Mapper;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Estado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DescuentoServiceTest {

    @Mock
    private DescuentoRepository descuentoRepository;

    @Mock
    private DiscoClient discoClient;

    @Mock
    private ProductoClient productoClient;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private DescuentoService descuentoService;

    //-----------------------obtener todos los descuentos------------------------
    @Test
    void debeRetornarTodosLosDescuentos() {
        Descuento descuentoVerano = new Descuento(1L, "Descuento Verano 2026",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 15.0
        );
        Descuento descuentoLanzamiento = new Descuento(2L, "Lanzamiento Nuevo",
                Estado.INACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );

        when(descuentoRepository.findAll()).thenReturn(List.of(descuentoVerano, descuentoLanzamiento));
        when(mapper.toDTO(descuentoVerano)).thenReturn(
                new DescuentoDTO(1L, "Descuento Verano 2026", Estado.ACTIVO, null, null, 15.0)
        );
        when(mapper.toDTO(descuentoLanzamiento)).thenReturn(
                new DescuentoDTO(2L, "Lanzamiento Nuevo", Estado.INACTIVO, null, null, 10.5)
        );

        List<DescuentoDTO> resultado = descuentoService.getAllDescuentos();

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
        Descuento descuentoLanzamiento = new Descuento(1L, "Lanzamiento Nuevo",
                Estado.INACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );
        when(descuentoRepository.findById(1L)).thenReturn(Optional.of(descuentoLanzamiento));
        when(mapper.toDTO(descuentoLanzamiento)).thenReturn(
                new DescuentoDTO(1L, "Lanzamiento Nuevo", Estado.INACTIVO, null, null, 10.5)
        );

        DescuentoDTO resultado = descuentoService.getDescuentoId(1L);

        assertThat(resultado.getNombre()).isEqualTo("Lanzamiento Nuevo");
        assertThat(resultado.getEstado()).isEqualTo(Estado.INACTIVO);
        assertThat(resultado.getDescuento()).isEqualTo(10.5);
        verify(descuentoRepository, times(1)).findById(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoNoExiste() {
        when(descuentoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> descuentoService.getDescuentoId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("99");
    }

    //---------------------------creación---------------------------------------------------------------

    @Test
    void debeGuardarDescuento() {
        Descuento descuentoLanzamiento = new Descuento(1L, "Lanzamiento Nuevo",
                Estado.INACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
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
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.5
        );
        when(descuentoRepository.findByNombre("Lanzamiento Nuevo")).thenReturn(Optional.of(descuento));
        when(mapper.toDTO(descuento)).thenReturn(
                new DescuentoDTO(1L, "Lanzamiento Nuevo", Estado.ACTIVO, null, null, 10.5)
        );

        DescuentoDTO resultado = descuentoService.getDescuentoNombre("Lanzamiento Nuevo");

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
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        Descuento nuevosDatos = new Descuento(null, "Modificado",
                Estado.INACTIVO, null, null, 20.0
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
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 15.0
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

    //---------------------------agregar disco (vía Feign)---------------------------------------------
    @Test
    void debeAgregarDiscoPorId() {
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));

        DiscoDTO discoDTO = new DiscoDTO(100L, "Thriller", "Michael Jackson", 15000);
        when(discoClient.obtenerDiscoPorId(100L)).thenReturn(ResponseEntity.ok(discoDTO));

        String resultado = descuentoService.agregarDisco("Verano", 100L);

        assertThat(resultado).isEqualTo("Disco agregado al descuento exitosamente");
        assertThat(descuento.getDiscoIds()).contains(100L);
        verify(descuentoRepository, times(1)).save(descuento);
    }

    @Test
    void debeLanzarExcepcionCuandoDiscoNoExisteEnFeign() {
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));
        when(discoClient.obtenerDiscoPorId(999L)).thenThrow(new RuntimeException("Not found"));

        assertThatThrownBy(() -> descuentoService.agregarDisco("Verano", 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Disco no encontrado");
    }

    //---------------------------quitar disco-----------------------------------------------------------
    @Test
    void debeQuitarDiscoPorId() {
        List<Long> ids = new ArrayList<>();
        ids.add(100L);
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, ids, new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));

        String resultado = descuentoService.quitarDisco("Verano", 100L);

        assertThat(resultado).isEqualTo("Disco eliminado del descuento exitosamente");
        assertThat(descuento.getDiscoIds()).doesNotContain(100L);
        verify(descuentoRepository, times(1)).save(descuento);
    }

    @Test
    void debeLanzarExcepcionCuandoDiscoNoEstaEnDescuento() {
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));

        assertThatThrownBy(() -> descuentoService.quitarDisco("Verano", 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("no está asociado");
    }

    //---------------------------agregar producto (vía Feign)------------------------------------------
    @Test
    void debeAgregarProductoPorId() {
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));

        ProductoDTO productoDTO = new ProductoDTO(200L, "Guitarra", "Fender", 150000);
        when(productoClient.obtenerProductoPorId(200L)).thenReturn(ResponseEntity.ok(productoDTO));

        String resultado = descuentoService.agregarProducto("Verano", 200L);

        assertThat(resultado).isEqualTo("Producto agregado al descuento exitosamente");
        assertThat(descuento.getProductoIds()).contains(200L);
        verify(descuentoRepository, times(1)).save(descuento);
    }

    @Test
    void debeLanzarExcepcionCuandoProductoNoExisteEnFeign() {
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));
        when(productoClient.obtenerProductoPorId(999L)).thenThrow(new RuntimeException("Not found"));

        assertThatThrownBy(() -> descuentoService.agregarProducto("Verano", 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Producto no encontrado");
    }

    //---------------------------quitar producto--------------------------------------------------------
    @Test
    void debeQuitarProductoPorId() {
        List<Long> ids = new ArrayList<>();
        ids.add(200L);
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), ids, 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));

        String resultado = descuentoService.quitarProducto("Verano", 200L);

        assertThat(resultado).isEqualTo("Producto eliminado del descuento exitosamente");
        assertThat(descuento.getProductoIds()).doesNotContain(200L);
        verify(descuentoRepository, times(1)).save(descuento);
    }

    @Test
    void debeLanzarExcepcionCuandoProductoNoEstaEnDescuento() {
        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, new ArrayList<>(), new ArrayList<>(), 10.0
        );
        when(descuentoRepository.findByNombre("Verano")).thenReturn(Optional.of(descuento));

        assertThatThrownBy(() -> descuentoService.quitarProducto("Verano", 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("no está asociado");
    }

    //---------------------------enriquecimiento con Feign---------------------------------------------
    @Test
    void debeEnriquecerDTOConNombresDeDiscos() {
        List<Long> discoIds = new ArrayList<>();
        discoIds.add(1L);
        List<Long> productoIds = new ArrayList<>();
        productoIds.add(2L);

        Descuento descuento = new Descuento(1L, "Verano",
                Estado.ACTIVO, discoIds, productoIds, 10.0
        );

        when(descuentoRepository.findById(1L)).thenReturn(Optional.of(descuento));

        DescuentoDTO dtoEnriquecido = new DescuentoDTO(1L, "Verano", Estado.ACTIVO,
                List.of("Michael Jackson - Thriller"), List.of("Guitarra - Fender"), 10.0
        );
        when(mapper.toDTO(descuento)).thenReturn(dtoEnriquecido);

        DescuentoDTO resultado = descuentoService.getDescuentoId(1L);

        assertThat(resultado.getDiscosAgregados()).contains("Michael Jackson - Thriller");
        assertThat(resultado.getProductosAgregados()).contains("Guitarra - Fender");
    }

}
