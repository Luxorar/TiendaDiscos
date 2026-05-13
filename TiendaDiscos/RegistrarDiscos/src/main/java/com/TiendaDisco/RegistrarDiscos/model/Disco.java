package com.TiendaDisco.RegistrarDiscos.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
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

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank(message = "Ingrese nombre del disco")
    String nombreDisco;

    private ArrayList<String> titulos = new ArrayList<>();

    @NotBlank(message = "Ingrese nombre del grupo o artista")
    String artista;

    @NotNull(message = "Ingrese el precio del disco")
    int precio;
}
