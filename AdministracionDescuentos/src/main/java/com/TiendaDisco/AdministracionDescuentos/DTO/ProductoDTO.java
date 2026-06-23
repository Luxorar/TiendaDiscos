package com.TiendaDisco.AdministracionDescuentos.DTO;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductoDTO {
    private Long id;
    private String nombreProducto;
    private String marca;
    private Integer precio;
}
