package com.TiendaDisco.ManejoStock.client.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class SedeDTO {
    private Long id;
    private String nombreSede;
    private String direccionSede;
    private String numberSedeTelefono;
}
