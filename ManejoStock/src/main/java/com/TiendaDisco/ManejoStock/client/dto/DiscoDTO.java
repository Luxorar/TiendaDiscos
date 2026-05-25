package com.TiendaDisco.ManejoStock.client.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class DiscoDTO {
    private Long id;
    private String nombreDisco;
    private String artista;
    private Integer precio;
}
