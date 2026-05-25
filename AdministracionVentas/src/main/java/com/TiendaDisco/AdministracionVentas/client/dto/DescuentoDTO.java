package com.TiendaDisco.AdministracionVentas.client.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class DescuentoDTO {
    private Long id;
    private String nombre;
    private String estado;
    private double descuento;
}
