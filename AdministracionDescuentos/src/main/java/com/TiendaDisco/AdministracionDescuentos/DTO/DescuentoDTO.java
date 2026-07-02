package com.TiendaDisco.AdministracionDescuentos.DTO;

import com.TiendaDisco.AdministracionDescuentos.model.Estado;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DescuentoDTO {
    private Long id;

    private String nombre;

    private Estado estado;

    private List<String> discosAgregados = new ArrayList<>();

    private List<String> productosAgregados = new ArrayList<>();

    private double descuento;
}
