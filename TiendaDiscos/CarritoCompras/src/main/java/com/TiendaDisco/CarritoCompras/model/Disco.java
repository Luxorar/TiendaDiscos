package com.TiendaDisco.CarritoCompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "DISCO")
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank(message= "Se debe ingresar un nombre") String nombreDisco;

    @Column(name = "artista")
    @NotBlank(message= "Se debe ingresar un artista") String artista;
    @NotNull(message= "Se debe ingresar un precio") int precio;
}