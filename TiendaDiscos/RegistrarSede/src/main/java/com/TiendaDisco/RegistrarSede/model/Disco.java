package com.TiendaDisco.RegistrarSede.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Disco")
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name="Nombre") String nombreDisco;

    @NotBlank(message= "Se debe ingresar un artista")
    @Column(name="Artista") String artista;

    @NotNull(message= "Se debe ingresar un precio")
    @Column(name="Precio") int precio;

    @OneToMany()
    private List<Sede> sedeList = new ArrayList<>();
}
