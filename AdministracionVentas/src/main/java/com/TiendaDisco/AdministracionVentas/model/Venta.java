package com.TiendaDisco.AdministracionVentas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "VENTAS")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany @JoinTable(
            name = "VENTA_PRODUCTOS",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosComprados = new ArrayList<>();

    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "puntos_usados")
    private int puntosUsados;

    @NotNull(message = "El usuario debe ganar puntos")
    @Column(name = "puntos_ganados")
    private int puntosGanados;

    @Column(name = "descuento")
    private int descuento;
}
