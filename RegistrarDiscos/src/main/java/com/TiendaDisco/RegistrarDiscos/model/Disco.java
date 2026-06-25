package com.TiendaDisco.RegistrarDiscos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "DISCOS")
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese nombre del disco")
    @Column(name = "nombre")
    private String nombreDisco;

    @NotBlank(message = "Ingrese nombre del grupo o artista")
    @Column(name = "artista")
    private String artista;

    @NotNull(message = "Ingrese el precio del disco")
    @Column(name = "precio")
    private Integer precio;

    @JsonIgnore
    @OneToMany(mappedBy = "disco")
    private List<Titulo> titulos = new ArrayList<>();
}