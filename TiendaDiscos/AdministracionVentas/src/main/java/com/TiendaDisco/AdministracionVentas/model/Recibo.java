package com.TiendaDisco.AdministracionVentas.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recibo {
    private String numeroFolio;
    @NotNull(message = "Falta ingresar IVA") private double iva;
    @NotNull(message = "Falta ingresar venta")private Venta venta;
    @NotNull(message = "Falta ingresar total pagado")private int totalPagado;
    @NotNull(message = "Falta ingresar metodo de pago")private MetodoPago metodoPago;
    private LocalDate fechaEmision;
    @NotNull(message = "Falta ingresar sede")private int sedeID;

}
