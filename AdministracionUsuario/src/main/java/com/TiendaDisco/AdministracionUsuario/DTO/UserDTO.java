package com.TiendaDisco.AdministracionUsuario.DTO;

import lombok.*;

import java.time.LocalDate;


/**
 * DTO que transporta los datos de un usuario entre las capas del sistema.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String userName;
    private LocalDate fechaRegistro;
    private int puntos;
}
