package com.TiendaDisco.AdministracionVentas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name="Ventas",
        description = "Microservicio dediacado a gestion de ventas"
)
public class Venta {

    @Schema(
            name="id",
            description = "Identificador unico de cada venta",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            name="Productos comprados",
            description = "Lista de productos comprados",
            example = "Tornamesa"
    )
    @JsonIgnore
    @ManyToMany @JoinTable(
            name = "VENTA_PRODUCTOS",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosComprados = new ArrayList<>();

    @Schema(
            name="Fecha de venta",
            description = "Fecha que identifica la relizacion de la venta",
            example = "14/06/2026"
    )
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @Schema(
            name="usuario id",
            description = "trae un usuario por su id",
            example = "1"
    )
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Schema(
            name="puntos usados",
            description = "identifica la cantidad de puntos utilizados para la compra",
            example = "1000"
    )
    @Column(name = "puntos_usados")
    private int puntosUsados;

    @Schema(
            name="puntos obtenidos",
            description = "identifica la cantidad de puntos obtenidos",
            example = "100"
    )
    @NotNull(message = "El usuario debe ganar puntos")
    @Column(name = "puntos_ganados")
    private int puntosGanados;

    @Schema(
            name="descuentos",
            description = "Muestra el porcentaje del descuento",
            example = "25%"
    )
    @Column(name = "descuento")
    private int descuento;
}
