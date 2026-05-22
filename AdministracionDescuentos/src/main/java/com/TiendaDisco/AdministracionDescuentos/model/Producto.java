package com.TiendaDisco.AdministracionDescuentos.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name="Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se debe ingresar un nombre")
    @Column(name = "nombre")
    private String nombreProducto;

    @NotBlank(message = "Se debe ingresar una marca")
    @Column(name = "marca")
    private String marca;

    @NotNull(message = "Se debe ingresar un precio")
    @Column(name = "precio")
    private int precio;
}