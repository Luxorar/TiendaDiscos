package com.TiendaDisco.AdministracionDescuentos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private Long id;
    @NotBlank(message= "Se debe ingresar un nombre") String nombreProducto;
    @NotBlank(message= "Se debe ingresar un marca") String marca;
    @NotNull(message= "Se debe ingresar un precio") int precio;
}
