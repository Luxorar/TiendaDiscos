package com.TiendaDisco.AdministracionVentas.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class VentaDTO {
    private Long id;
    private List<String> productosComprados;
    private LocalDate fechaVenta;
    private String usuario;
    private int puntosUsados;
    private int puntosGanados;
    private int subtotal;
    private int descuento;
    private int totalPagar;
}