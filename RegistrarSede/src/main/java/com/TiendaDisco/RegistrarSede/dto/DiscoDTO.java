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
public class DiscoDTO {
    private Long id;
    private String nombreDisco;
    private String artista;
    private int precio;
}
