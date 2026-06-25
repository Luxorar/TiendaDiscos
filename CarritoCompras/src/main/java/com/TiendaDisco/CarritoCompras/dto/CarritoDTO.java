package com.TiendaDisco.CarritoCompras.dto;

import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
