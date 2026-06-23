package com.TiendaDisco.RegistrarDiscos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "DISCOS")
@Schema(
        name="Disco",
        description="micreoservicio dedicado a guardar discos"
)
public class Disco {

    @Schema(
            title = "id",
            description = "Identificador unico para los discos",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            title = "nombre",
            description = "Nombre del disco",
            example = "Thriller"
    )
    @NotBlank(message = "Ingrese nombre del disco")
    @Column(name = "nombre")
    private String nombreDisco;

    @Schema(
            title = "artista",
            description = "Nombre de la banda o artista",
            example = "Micheal Jackson"
    )
    @NotBlank(message = "Ingrese nombre del grupo o artista")
    @Column(name = "artista")
    private String artista;

    @Schema(
            title="precio",
            description="Precio del disco",
            example="15000"
    )
    @NotNull(message = "Ingrese el precio del disco")
    @Column(name = "precio")
    private Integer precio;

    @Schema(
            title="Titulo",
            description="Lista de canciones del disco",
            example="Thriller"
    )
    @ManyToOne
    @JoinColumn(name = "titulo_id")
    private Titulo titulo;
}