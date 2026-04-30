package com.TiendaDisco.AdministracionVentas.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private Long id;
    @NotBlank(message= "Se debe ingresar un nombre") String nombre;
    @NotBlank(message= "Se debe ingresar un precio") int precio;
}