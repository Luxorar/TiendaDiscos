package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.exception.ManejoErrores;
import com.TiendaDisco.ManejoStock.mapper.Mapper;
import com.TiendaDisco.ManejoStock.model.Producto;
import com.TiendaDisco.ManejoStock.model.TipoProducto;
import com.TiendaDisco.ManejoStock.model.infoStock;
import com.TiendaDisco.ManejoStock.repository.InfoStockRepository;
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
class InfoStockServiceTest {

    @Mock
    private InfoStockRepository infoStockRepo;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private InfoStockService service;

    @Test
    void getAllInfoStock_shouldReturnAllStock() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);

        infoStock entity = new infoStock();
        entity.setId(1L);
        entity.setProducto(producto);
        entity.setSede(1L);
        entity.setStockActual(10);

        InfoStockDTO dto = InfoStockDTO.builder().id(1L).nombreProducto("Guitarra").nombreSede("Sede Central").stockActual(10).build();

        when(infoStockRepo.findAll()).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        List<InfoStockDTO> result = service.getAllInfoStock();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Guitarra");
        verify(infoStockRepo).findAll();
    }

    @Test
    void postInfoStock_shouldSaveAndReturn() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);

        infoStock stock = new infoStock();
        stock.setProducto(producto);
        stock.setSede(1L);
        stock.setStockActual(10);

        when(infoStockRepo.save(stock)).thenReturn(stock);

        infoStock result = service.postInfoStock(stock);

        assertThat(result).isSameAs(stock);
        verify(infoStockRepo).save(stock);
    }

    @Test
    void getSedeInfo_happy_shouldReturnStockForSede() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);

        infoStock stock = new infoStock();
        stock.setId(1L);
        stock.setProducto(producto);
        stock.setSede(1L);
        stock.setStockActual(10);

        InfoStockDTO dto = InfoStockDTO.builder().id(1L).nombreProducto("Bajo").nombreSede("Centro").stockActual(10).build();

        when(infoStockRepo.findAll()).thenReturn(List.of(stock));
        when(mapper.toDTO(stock)).thenReturn(dto);

        List<InfoStockDTO> result = service.getSedeInfo("Centro");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Bajo");
        verify(infoStockRepo).findAll();
    }

    @Test
    void getSedeInfo_notFound_shouldThrow() {
        when(infoStockRepo.findAll()).thenReturn(List.of());

        assertThatThrownBy(() -> service.getSedeInfo("NoExiste"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No se encontró stock para la sede: NoExiste");
    }

    @Test
    void getProductoInfo_happy_shouldReturnStock() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);

        infoStock stock = new infoStock();
        stock.setId(1L);
        stock.setProducto(producto);
        stock.setSede(1L);
        stock.setStockActual(10);

        InfoStockDTO dto = InfoStockDTO.builder().id(1L).nombreProducto("Test").nombreSede("Sede").stockActual(10).build();

        when(infoStockRepo.findAll()).thenReturn(List.of(stock));
        when(mapper.toDTO(stock)).thenReturn(dto);

        InfoStockDTO result = service.getProductoInfo("Test");

        assertThat(result.getNombreProducto()).isEqualTo("Test");
        verify(infoStockRepo).findAll();
    }

    @Test
    void getProductoInfo_notFound_shouldThrow() {
        when(infoStockRepo.findAll()).thenReturn(List.of());

        assertThatThrownBy(() -> service.getProductoInfo("No existe"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No se encontró información para el producto: No existe");
    }

    @Test
    void getInfoID_happy_shouldReturnStock() {
        Producto producto = new Producto();
        producto.setTipoProducto(TipoProducto.PRODUCTO);
        producto.setIdProducto(1L);

        infoStock stock = new infoStock();
        stock.setId(1L);
        stock.setProducto(producto);
        stock.setSede(1L);
        stock.setStockActual(10);

        InfoStockDTO dto = InfoStockDTO.builder().id(1L).nombreProducto("Piano").nombreSede("Sede").stockActual(10).build();

        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));
        when(mapper.toDTO(stock)).thenReturn(dto);

        InfoStockDTO result = service.getInfoID(1L);

        assertThat(result.getNombreProducto()).isEqualTo("Piano");
        verify(infoStockRepo).findById(1L);
    }

    @Test
    void getInfoID_notFound_shouldThrow() {
        when(infoStockRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getInfoID(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("ID de stock no encontrado: 99");
    }

    @Test
    void putStock_happy_shouldUpdateStock() {
        infoStock stock = new infoStock();
        stock.setStockActual(0);
        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));

        String result = service.putStock(1L, 50);

        assertThat(result).contains("50");
        assertThat(stock.getStockActual()).isEqualTo(50);
        verify(infoStockRepo).save(stock);
    }

    @Test
    void putStock_notFound_shouldThrow() {
        when(infoStockRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putStock(99L, 10))
                .isInstanceOf(ManejoErrores.class);
    }

    @Test
    void deleteInfo_happy_shouldDelete() {
        infoStock stock = new infoStock();
        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));

        String result = service.deleteInfo(1L);

        assertThat(result).contains("eliminado");
        verify(infoStockRepo).delete(stock);
    }

    @Test
    void deleteInfo_notFound_shouldThrow() {
        when(infoStockRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteInfo(99L))
                .isInstanceOf(ManejoErrores.class);
    }
}
