package com.TiendaDisco.ManejoStock.mapper;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.model.Sede;
import com.TiendaDisco.ManejoStock.model.infoStock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void toDTO_WithValidInfoStockAndSede_ShouldMapAllFields() {
        Sede sede = new Sede(1L, "Sede Principal", "Av. Central 456");
        infoStock stock = new infoStock(1L, "Guitarra Eléctrica", sede, 50);

        InfoStockDTO dto = Mapper.toDTO(stock);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Guitarra Eléctrica", dto.getNombreProducto()),
                () -> assertEquals("Sede Principal", dto.getNombreSede()),
                () -> assertEquals(50, dto.getStockActual())
        );
    }

    @Test
    void toDTO_WithValidInfoStockAndNullSede_ShouldMapWithoutSede() {
        infoStock stock = new infoStock(1L, "Guitarra Eléctrica", null, 50);

        InfoStockDTO dto = Mapper.toDTO(stock);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Guitarra Eléctrica", dto.getNombreProducto()),
                () -> assertNull(dto.getNombreSede()),
                () -> assertEquals(50, dto.getStockActual())
        );
    }

    @Test
    void toDTO_WithNullInfoStock_ShouldReturnNull() {
        InfoStockDTO dto = Mapper.toDTO(null);
        assertNull(dto);
    }
}
