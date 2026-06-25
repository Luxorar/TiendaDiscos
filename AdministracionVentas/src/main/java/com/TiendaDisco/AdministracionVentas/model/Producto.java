package com.TiendaDisco.AdministracionVentas.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    int precio;

    @Column(name = "id_producto")
    Long idProducto;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo")
    TipoProducto tipo;
}