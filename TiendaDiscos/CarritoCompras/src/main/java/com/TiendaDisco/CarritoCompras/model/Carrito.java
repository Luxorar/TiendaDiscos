package com.TiendaDisco.CarritoCompras.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
    @NotBlank(message = "Campo de nombre obligatorio") private String userName;
    private ArrayList<String> productosAgregadosNombres = new ArrayList<>();
    @NotNull(message = "Campo de la suma de precios de los productos obligatorio") private int sumaPrecios;
    private double descuento;
}
