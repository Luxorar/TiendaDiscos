package com.TiendaDisco.RegistroResenas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name="Resena",
        description = "microservicio capaz de obtener las resenas de los usuarios"
)
public class Disco {
    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name = "nombre") String nombreDisco;

    @NotBlank(message= "Se debe ingresar un artista")
    @Column(name = "artista")String artista;

    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @JsonIgnore
    @OneToMany(mappedBy = "disco")
    private List<Resena> resenaList = new ArrayList<>();
}
