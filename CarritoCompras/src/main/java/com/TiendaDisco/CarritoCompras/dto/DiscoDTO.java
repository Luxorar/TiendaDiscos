package com.TiendaDisco.CarritoCompras.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscoDTO {
    private Long id;
    private String nombreDisco;
    private String artista;
    private int precio;
    private String imagen;
}
