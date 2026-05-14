package com.TiendaDisco.RegistrarDiscos.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder @Entity
@Table(name="DISCOS")
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese nombre del disco")
    @Column(name = "nombre")
    private String nombreDisco;

    @NotBlank(message = "Ingrese nombre del grupo o artista")
    @Column(name="artista")
    private String artista;

    @NotNull(message = "Ingrese el precio del disco")
    @Column(name="precio")
    private int precio;

    @ManyToOne
    @JoinColumn(name="titulo_id")
    private Titulo titulo;

}
