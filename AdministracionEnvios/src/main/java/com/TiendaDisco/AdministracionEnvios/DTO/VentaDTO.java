package com.TiendaDisco.AdministracionEnvios.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO que transporta los datos de una venta entre capas.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
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