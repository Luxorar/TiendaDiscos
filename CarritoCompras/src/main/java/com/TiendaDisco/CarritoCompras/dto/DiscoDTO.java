package com.TiendaDisco.CarritoCompras.dto;

import lombok.*;

/**
 * DTO que transporta los datos de un disco.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscoDTO {
    private Long id;
    private String nombreDisco;
    private String artista;
    private int precio;
}
