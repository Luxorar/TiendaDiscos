package com.TiendaDisco.ManejoStock.DTO;

import lombok.*;

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
