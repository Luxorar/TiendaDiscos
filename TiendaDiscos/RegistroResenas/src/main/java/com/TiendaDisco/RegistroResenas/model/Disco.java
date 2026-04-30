package com.TiendaDisco.RegistroResenas.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disco {
    private Long id;
    @NotBlank(message= "Se debe ingresar un nombre") String nombreDisco;
    @NotBlank(message= "Se debe ingresar un artista") String artista;
}
