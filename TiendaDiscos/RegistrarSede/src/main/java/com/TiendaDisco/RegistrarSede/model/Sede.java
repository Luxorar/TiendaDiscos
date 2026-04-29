package com.TiendaDisco.RegistrarSede.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sede {
    private Long id;
    @NotBlank(message = "nombre de Sede obligatorio") private String nombreSede;
    @NotBlank(message = "dirección de Sede obligatorio")private String direccionSede;
    @NotBlank(message = "numero de Sede obligatorio")private String numberSedeTelefono;
    private ArrayList<String> productosDisponibleNombre = new ArrayList<>();
}
