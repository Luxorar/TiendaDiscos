package com.TiendaDisco.AdministracionVentas.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un producto asociado a una venta.
 * <p>Almacena la informacion basica del producto, incluyendo su tipo
 * ({@link TipoProducto}) y el precio al momento de la venta.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identificador único del disco o producto.
     * Dos entidades pueden compartir el mismo {@code idProducto} si son de distinto tipo
     * (p. ej., un disco con id=1 y un producto con id=1 se consideran diferentes).
     */
    @Column(name = "id_producto")
    Long idProducto;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo")
    TipoProducto tipo;
}