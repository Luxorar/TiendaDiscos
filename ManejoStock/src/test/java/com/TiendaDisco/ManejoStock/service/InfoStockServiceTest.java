package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.exception.ManejoErrores;
import com.TiendaDisco.ManejoStock.mapper.Mapper;
import com.TiendaDisco.ManejoStock.model.Sede;
import com.TiendaDisco.ManejoStock.model.infoStock;
import com.TiendaDisco.ManejoStock.repository.InfoStockRepository;
import com.TiendaDisco.ManejoStock.repository.SedeRepository;
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
    private SedeRepository sedeRepo;

    @InjectMocks
    private InfoStockService service;

    @Test
    void getAllInfoStock_shouldReturnAllStock() {
        infoStock entity = new infoStock();
        entity.setNombreProducto("Guitarra");
        when(infoStockRepo.findAll()).thenReturn(List.of(entity));

        List<InfoStockDTO> result = service.getAllInfoStock();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Guitarra");
        verify(infoStockRepo).findAll();
    }

    @Test
    void postInfoStock_shouldSaveAndReturn() {
        infoStock stock = new infoStock();
        when(infoStockRepo.save(stock)).thenReturn(stock);

        infoStock result = service.postInfoStock(stock);

        assertThat(result).isSameAs(stock);
        verify(infoStockRepo).save(stock);
    }

    @Test
    void getSedeInfo_shouldReturnStockForSede() {
        infoStock stock = new infoStock();
        stock.setNombreProducto("Bajo");
        when(infoStockRepo.findBySede_NombreSede("Centro")).thenReturn(List.of(stock));

        List<InfoStockDTO> result = service.getSedeInfo("Centro");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Bajo");
        verify(infoStockRepo).findBySede_NombreSede("Centro");
    }

    @Test
    void getProductoInfo_happy_shouldReturnStock() {
        infoStock stock = new infoStock();
        stock.setNombreProducto("Test");
        when(infoStockRepo.findByNombreProducto("Test")).thenReturn(Optional.of(stock));

        InfoStockDTO result = service.getProductoInfo("Test");

        assertThat(result.getNombreProducto()).isEqualTo("Test");
        verify(infoStockRepo).findByNombreProducto("Test");
    }

    @Test
    void getProductoInfo_notFound_shouldThrow() {
        when(infoStockRepo.findByNombreProducto("No existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getProductoInfo("No existe"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No se encontró información para el producto: No existe");
    }

    @Test
    void getInfoID_happy_shouldReturnStock() {
        infoStock stock = new infoStock();
        stock.setNombreProducto("Piano");
        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));

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
    void putNombreProducto_happy_shouldUpdateName() {
        infoStock stock = new infoStock();
        stock.setNombreProducto("Old");
        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));

        String result = service.putNombreProducto(1L, "New");

        assertThat(result).contains("New");
        assertThat(stock.getNombreProducto()).isEqualTo("New");
        verify(infoStockRepo).save(stock);
    }

    @Test
    void putNombreProducto_notFound_shouldThrow() {
        when(infoStockRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putNombreProducto(99L, "New"))
                .isInstanceOf(ManejoErrores.class);
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
    void putSede_happy_shouldUpdateSede() {
        infoStock stock = new infoStock();
        Sede sede = new Sede();
        sede.setNombreSede("NewSede");
        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));
        when(sedeRepo.findByNombreSede("NewSede")).thenReturn(Optional.of(sede));

        String result = service.putSede(1L, "NewSede");

        assertThat(result).contains("NewSede");
        assertThat(stock.getSede()).isSameAs(sede);
        verify(infoStockRepo).save(stock);
    }

    @Test
    void putSede_notFoundStock_shouldThrow() {
        when(infoStockRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putSede(99L, "NewSede"))
                .isInstanceOf(ManejoErrores.class);
    }

    @Test
    void putSede_sedeNotFound_shouldThrow() {
        infoStock stock = new infoStock();
        when(infoStockRepo.findById(1L)).thenReturn(Optional.of(stock));
        when(sedeRepo.findByNombreSede("NoExiste")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putSede(1L, "NoExiste"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("La sede especificada no existe: NoExiste");
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
