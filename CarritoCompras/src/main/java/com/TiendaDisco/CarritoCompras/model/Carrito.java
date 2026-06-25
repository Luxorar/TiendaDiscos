package com.TiendaDisco.CarritoCompras.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "CARRITO")
@Schema(
        name="Carrito",
        description = "Microservicio capaz de gestionar carritos"
)
public class Carrito {

    @Schema(
            name="id",
            description = "Identificador unico del carrito",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Schema(
            name="usuario",
            description = "Dueño del carrito",
            example = "Juanito"
    )
    @NotNull(message = "Campo de usuario obligatorio")
    @Column(name="user_id")
    private Long userId;

    @Schema(
            name="Productos agregados",
            description = "Lista de productos agregados"
    )
    @ManyToMany @JoinTable(
            name = "CARRITO_PRODUCTOS",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosAgregados = new ArrayList<>();

    @Schema(
            name="Discos agregados",
            description = "lista de discos agregados"
    )
    @ManyToMany @JoinTable(
            name = "CARRITO_DISCOS",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "disco_id")
    )
    private List<Disco> discosAgregados = new ArrayList<>();

    @Schema(
            name="Descuento",
            description = "descuento aplicado al carrito",
            example = "0.50"
    )
    @Column(name= "descuento")
    private double descuento;
}
