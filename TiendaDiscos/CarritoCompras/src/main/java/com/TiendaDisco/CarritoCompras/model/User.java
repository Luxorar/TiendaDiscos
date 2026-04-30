package com.TiendaDisco.CarritoCompras.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @NotBlank(message= "Se debe ingresar un nombre") String userName;
    @NotBlank(message= "Se debe ingresar un gmail") String gmail;
}
