package com.TiendaDisco.CarritoCompras.dto;

import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * DTO que transporta los datos del carrito de compras entre capas.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoDTO {
    private Long id;

    private String user;

    private int precioSolid;

    private List<Producto> productosAgregados = new ArrayList<>();

    private List<Disco> discosAgregados = new ArrayList<>();

    private double descuento;

    private int precioLiquido;
}
