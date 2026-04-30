package com.TiendaDisco.AdministracionVentas.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    private Long id;
    private ArrayList<Producto> productosComprados = new ArrayList<>();
    private LocalDate fechaVenta;
    @NotBlank(message= "Se debe ingresar un usuario") Usuario usuario;
    private int puntosUsados;
    @NotNull(message = "El usuario debe ganar puntos") int puntosGanados;
    @NotBlank(message= "Se debe ingresar el monto sin descuentos") int subtotal;
    private int descuento;
}
