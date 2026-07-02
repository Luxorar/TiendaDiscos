package com.TiendaDisco.AdministracionDescuentos.DTO;

import lombok.*;

/**
 * DTO que transporta los datos de un producto.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductoDTO {
    private Long id;
    private String nombreProducto;
    private String marca;
    private Integer precio;
}
