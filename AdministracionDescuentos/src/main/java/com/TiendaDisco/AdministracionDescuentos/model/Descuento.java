package com.TiendaDisco.AdministracionDescuentos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name="DESCUENTO")
@Schema(
        name="Descuento",
        description = "Microservicio capaz de gestionar descuentos"
)
public class Descuento {

    @Schema(
            name = "id",
            description = "Identificador unico del descuento",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            name = "Nombre",
            description = "Nombre del descuento",
            example = "Descuento cyber day"
    )
    @NotBlank(message = "Ingrese nombre del descuento")
    @Column(name = "nombre_descuento")
    private String nombre;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ElementCollection
    @CollectionTable(name = "DESCUENTO_DISCOS", joinColumns = @JoinColumn(name = "descuento_id"))
    @Column(name = "disco_id")
    private List<Long> discoIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "DESCUENTO_PRODUCTOS", joinColumns = @JoinColumn(name = "descuento_id"))
    @Column(name = "producto_id")
    private List<Long> productoIds = new ArrayList<>();

    @Schema(
            name="Descuento",
            description = "Descuento aplicado",
            example = "0.50"
    )
    @Column(name = "descuento")
    private double descuento;
}