package com.TiendaDisco.ManejoStock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="INFO_STOCK")
@Schema(
        name="Info Stock",
        description = "Microservicio capaz de gestionar el stock"
)
public class infoStock {

    @Schema(
            name="id",
            description = "Identificador unico del stock",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            name="nombre producto",
            description = "Nombre del producto",
            example = "Tornamesa"
    )
    @NotBlank(message = "Ingreso de nombre de producto obligatorio")
    @Column(name="nombre_producto")
    private String nombreProducto;

    @Schema(
            name="sede",
            description = "Sede identificada por su id",
            example = "1"
    )
    @NotNull(message = "Ingreso de sede obligatorio")
    @ManyToOne
    @JoinColumn(name="sede_id")
    private Sede sede;

    @Schema(
            name="Stock",
            description = "Cantidad de productos",
            example = "10"
    )
    @Column(name="stock_actual")
    private int stockActual;
}