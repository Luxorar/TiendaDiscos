package com.TiendaDisco.ManejoStock.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="INFO_STOCK")
public class infoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingreso de nombre de producto obligatorio")
    @Column(name="nombre_producto")
    private String nombreProducto;

    @NotNull(message = "Ingreso de sede obligatorio")
    @ManyToOne
    @JoinColumn(name="sede_id")
    private Sede sede;

    @Column(name="stock_actual")
    private int stockActual;
}