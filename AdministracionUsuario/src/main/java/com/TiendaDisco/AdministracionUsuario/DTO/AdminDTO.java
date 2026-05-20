package com.TiendaDisco.AdministracionUsuario.DTO;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;


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
