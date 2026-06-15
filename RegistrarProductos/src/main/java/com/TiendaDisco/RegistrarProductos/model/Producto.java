package com.TiendaDisco.RegistrarProductos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "PRODUCTO")
@Schema(
        name = "Producto",
        description = "microservicio dedicado a administrar productos"
)
public class Producto {

    @Schema(
            title = "id",
            description = "Identificador unico de los productos",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            title = "Nombre",
            description = "Nombre del producto",
            example = "Tornamesa"
    )
    @NotBlank(message = "Se debe ingresar un nombre de producto")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Schema(
            title = "Marca",
            description = "Marca del producto",
            example = "audio-technica"
    )
    @NotBlank(message = "Se debe ingresar una marca")
    @Column(name = "marca")
    private String marca;

    @Schema(
            title = "precio",
            description = "Precio del producto",
            example = "250000"
    )
    @NotNull(message = "Se debe ingresar un precio")
    @Column(name = "precio")
    private Integer precio;
}