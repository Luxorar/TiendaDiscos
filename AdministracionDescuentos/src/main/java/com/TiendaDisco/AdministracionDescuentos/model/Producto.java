package com.TiendaDisco.AdministracionDescuentos.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {

    private Long id;

    private String nombreProducto;

    private String marca;

    @NotNull(message = "Se debe ingresar un precio")
    @Column(name = "precio")
    private int precio;
}