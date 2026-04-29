package com.TiendaDisco.RegistrarDiscos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public class Disco {
    private Long id;
    @NotBlank(message = "Ingrese nombre del disco") String nombreDisco;
    private ArrayList<String> titulos = new ArrayList<>();
    @NotBlank(message = "Ingrese nombre del grupo o artista") String artista;
    @NotNull(message = "Ingrese el precio del disco") int precio;

}
