package com.TiendaDisco.CarritoCompras.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoDiscoDTO {
    private Long id;
    private Long discoId;
    private int qty;
    private String nombreDisco;
    private String artista;
    private int precio;
    private String imagen;
}
