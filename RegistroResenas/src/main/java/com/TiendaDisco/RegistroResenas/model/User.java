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
@Table(name = "USUARIO")
@Schema(
        name="Resena",
        description = "microservicio capaz de obtener las resenas de los usuarios"
)
public class User {
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
    @Column(name = "nombre")
    private String userName;

    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @NotBlank(message= "Se debe ingresar un gmail")
    @Column(name = "gmail")
    private String gmail;

    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Resena> resenaList = new ArrayList<>();

}
