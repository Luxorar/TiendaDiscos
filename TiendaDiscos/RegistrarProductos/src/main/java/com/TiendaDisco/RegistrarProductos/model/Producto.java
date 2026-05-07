package com.TiendaDisco.RegistrarProductos.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "Ingrese nombre del producto")
    String nombreProducto;

    @NotBlank(message = "Ingrese marca del producto")
    String marcaProducto;

    @NotNull(message = "Ingrese precio del producto")
    int precio;
}