package com.TiendaDisco.AdministracionVentas.dto;

import lombok.*;

import java.time.LocalDate;


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
