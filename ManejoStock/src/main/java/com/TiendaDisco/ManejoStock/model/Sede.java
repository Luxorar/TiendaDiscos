package com.TiendaDisco.ManejoStock.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Table(name="SEDE")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se debe ingresar un nombre")
    @Column(name="nombre_sede")
    private String nombreSede;

    @NotBlank(message = "Se debe ingresar una dirección")
    @Column(name="direccion_sede")
    private String direccionSede;
}