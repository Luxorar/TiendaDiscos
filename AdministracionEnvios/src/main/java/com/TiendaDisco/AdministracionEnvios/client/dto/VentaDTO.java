package com.TiendaDisco.AdministracionEnvios.client.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class VentaDTO {
    private Long id;
    private String fechaVenta;
    private String usuario;
    private int subtotal;
    private int descuento;
    private int totalPagar;
}
