package com.TiendaDisco.AdministracionDescuentos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder @Entity
@Table(name="DISCO")
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name="nombre")
    private String nombreDisco;

    @NotBlank(message= "Se debe ingresar un artista")
    @Column(name = "artista")
    private String artista;

    @NotNull(message= "Se debe ingresar un precio")
    @Column (name = "precio")
    private int precio;


}
