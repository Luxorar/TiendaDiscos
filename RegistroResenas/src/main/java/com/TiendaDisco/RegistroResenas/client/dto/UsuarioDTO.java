package com.TiendaDisco.RegistroResenas.client.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class UsuarioDTO {
    private Long id;
    private String userName;
    private LocalDate fechaRegistro;
    private int puntos;
}
