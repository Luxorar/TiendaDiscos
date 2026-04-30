package com.TiendaDisco.ManejoStock.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disco extends infoStock{
    @NotBlank(message = "Se debe ingresar un artista")private String artista;
}
