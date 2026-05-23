package com.TiendaDisco.ManejoStock.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Table(name="DISCO_STOCK")
public class Disco extends infoStock {

    @NotBlank(message = "Se debe ingresar un artista")
    @Column(name="artista")
    private String artista;
}