package com.TiendaDisco.AdministracionUsuario.DTO;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;


/**
 * DTO que transporta los datos de un administrador entre las capas del sistema.
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
public class AdminDTO {
    private Long id;
    private String userName;
    private LocalDate fechaRegistro;
}
