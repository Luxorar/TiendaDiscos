package com.TiendaDisco.CarritoCompras.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Campo de usuario obligatorio") private User user;
    private ArrayList<Producto> productosAgregados = new ArrayList<>();
    private ArrayList<Disco> discosAgregados = new ArrayList<>();
    @NotNull(message = "Campo de la suma de precios de los productos obligatorio") private int sumaPrecios;
    private double descuento;
}
