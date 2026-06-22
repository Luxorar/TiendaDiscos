package com.TiendaDisco.RegistrarSede.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {
    private Long id;
    private String nombreSede;
    private String direccionSede;
    private String numberSedeTelefono;
}
