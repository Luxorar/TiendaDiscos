package com.TiendaDisco.CarritoCompras.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * DTO que transporta los datos basicos de un usuario.
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
public class UserDTO {
    private Long id;
    private String userName;
    private LocalDate fechaRegistro;
    private int puntos;
}
