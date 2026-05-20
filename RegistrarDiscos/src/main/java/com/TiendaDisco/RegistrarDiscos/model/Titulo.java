package com.TiendaDisco.RegistrarDiscos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder @Entity
@Table(name = "TITULO")
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un titulo")
    @Column(name = "titulo")
    private String titulo;

    @OneToMany(mappedBy = "titulo")
    private List<Disco> titulosList = new ArrayList<>();
}
