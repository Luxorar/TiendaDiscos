package com.TiendaDisco.ManejoStock.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class infoStock {
    private Long id;
    @NotBlank(message = "Ingreso de nombre de producto obligatorio") private String productName;
    @NotNull(message = "Ingreso de tipo de producto obligatorio") private Tipo tipo;
    @NotNull(message = "Ingreso de sede obligatorio") private Long sedeID;
    private int stockActual;




}
