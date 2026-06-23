package com.TiendaDisco.RegistroResenas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "DISCO_B")
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name = "nombre") String nombreDisco;

    @NotBlank(message= "Se debe ingresar un artista")
    @Column(name = "artista")String artista;

    @JsonIgnore
    @OneToMany(mappedBy = "disco")
    private List<Resena> resenaList = new ArrayList<>();
}
