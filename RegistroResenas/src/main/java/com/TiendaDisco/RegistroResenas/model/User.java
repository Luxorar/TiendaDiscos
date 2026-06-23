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
@Table(name = "USUARIO_D")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name = "nombre")
    private String userName;

    @NotBlank(message= "Se debe ingresar un gmail")
    @Column(name = "gmail")
    private String gmail;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Resena> resenaList = new ArrayList<>();

}
