package com.TiendaDisco.RegistrarSede.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name="PRODUCTOS_B")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name="nombre")  String nombreProducto;

    @NotNull(message= "Se debe ingresar un precio")
    @Column(name="precio") int precio;
}
