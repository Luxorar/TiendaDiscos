package com.TiendaDisco.ManejoStock.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sede {
    private Long id;
    @NotBlank(message= "Se debe ingresar un nombre") String nombreSede;
    @NotBlank(message= "Se debe ingresar una dirección") String direccionSede;
}
