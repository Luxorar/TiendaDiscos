package com.TiendaDisco.CarritoCompras.model;


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
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @NotNull(message = "Campo de usuario obligatorio")
    @Column(name="user_id")
    private Long userId;

    @ManyToMany @JoinTable(
            name = "CARRITO_PRODUCTOS",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosAgregados = new ArrayList<>();


    @ManyToMany @JoinTable(
            name = "CARRITO_DISCOS",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "disco_id")
    )
    private List<Disco> discosAgregados = new ArrayList<>();

    @Column(name= "descuento")
    private double descuento;
}
