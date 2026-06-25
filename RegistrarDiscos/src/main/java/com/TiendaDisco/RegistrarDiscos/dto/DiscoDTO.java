package com.TiendaDisco.RegistrarDiscos.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class DiscoDTO {
    private Long id;
    private String nombreDisco;
    private String artista;
    private Integer precio;
    private List<String> titulos;
}
