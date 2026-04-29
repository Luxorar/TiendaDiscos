package com.TiendaDisco.AdministracionDescuentos.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Descuento {
    private Long id;
    @NotBlank(message = "Ingrese nombre del descuento") String nombre;
    private ArrayList<String> productosConDescuento = new ArrayList<>();
    private Estado estado;
}
