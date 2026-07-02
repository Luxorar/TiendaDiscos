package com.TiendaDisco.AdministracionVentas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Entidad que representa una venta realizada por un usuario.
 * <p>Contiene los productos comprados, fechas, puntos y descuentos
 * asociados a la transaccion.</p>
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
@Table(name = "VENTAS")
public class Venta {
    /**
     * Identificador unico de la venta.
     * se genera de forma automatica por la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Lista de productos que incluye la venta.
     */
    @ManyToMany @JoinTable(
            name = "VENTA_PRODUCTOS",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosComprados = new ArrayList<>();

    /**
     * Fecha en donde se registra la venta.
     */
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    /**
     * Identificador del usuario correspondiente a quien hizo la venta
     * se comunica con el microservicio Administración usuario
     */
    @Column(name = "usuario_id")
    private Long usuario;

    /**
     * Puntos usados a modo de descuento al momento d erealizar una compra
     */
    @Column(name = "puntos_usados")
    private int puntosUsados;

    /**
     * Puntos ganados por el usuario cuando realiza la compra
     */
    @NotNull(message = "El usuario debe ganar puntos")
    @Column(name = "puntos_ganados")
    private int puntosGanados;

    /**
     * Descuento aplicado en la venta
     * se usa un numero entero para representar el porcentaje, es decir
     */
    @Column(name = "descuento")
    private int descuento;
}
