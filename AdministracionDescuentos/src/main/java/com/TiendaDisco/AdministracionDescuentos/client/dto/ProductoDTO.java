package com.TiendaDisco.AdministracionDescuentos.client.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductoDTO {
    private Long id;
    private String nombreProducto;
    private String marca;
    private Integer precio;
}
