package com.TiendaDisco.AdministracionUsuario.DTO;

import lombok.*;

import java.math.BigDecimal;
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
    private BigDecimal credito;
    private Boolean modoOscuro;
}
