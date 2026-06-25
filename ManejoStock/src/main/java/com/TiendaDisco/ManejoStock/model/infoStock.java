package com.TiendaDisco.ManejoStock.model;

import jakarta.persistence.*;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ForeignKey;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="INFO_STOCK")
public class infoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingreso de producto obligatorio")
    @ManyToOne
    @JoinColumn(name = "producto", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Producto producto;

    @NotNull(message = "Ingreso de sede obligatorio")
    @Column(name="sede_id")
    private Long sede;

    @Column(name="stock_actual")
    private int stockActual;
}