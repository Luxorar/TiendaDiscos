package com.TiendaDisco.AdministracionVentas.client.dto;

import lombok.*;
import java.util.ArrayList;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CarritoDTO {
    private Long id;
    private String user;
    private int precioSolid;
    private double descuento;
    private int precioLiquido;
}
