package com.TiendaDisco.RegistrarProductos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "PRODUCTO_B")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se debe ingresar un nombre de producto")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotBlank(message = "Se debe ingresar una marca")
    @Column(name = "marca")
    private String marca;

    @NotNull(message = "Se debe ingresar un precio")
    @Column(name = "precio")
    private Integer precio;
}