package com.TiendaDisco.CarritoCompras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "CARRITO_DISCOS")
@Schema(description = "Referencia a un disco en el carrito con cantidad")
public class CarritoDisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carrito_id", insertable = false, updatable = false)
    private Long carritoId;

    @NotNull
    @Column(name = "disco_id")
    private Long discoId;

    @Column(name = "qty")
    private int qty = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id", nullable = false)
    @JsonIgnore
    private Carrito carrito;
}
