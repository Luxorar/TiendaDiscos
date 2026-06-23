package com.TiendaDisco.ManejoStock.DTO;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoStockDTO {
    private Long id;
    private String nombreProducto;
    private String nombreSede;
    private int stockActual;
}
