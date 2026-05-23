package com.TiendaDisco.ManejoStock.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Table(name="PRODUCTO_STOCK")
public class Producto extends infoStock {

    @NotBlank(message = "Se debe ingresar una marca")
    @Column(name="marca")
    private String marca;
}