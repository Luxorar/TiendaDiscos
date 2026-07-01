package com.TiendaDisco.RegistroResenas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un usuario.
 * Esta clase esta mapeada a la tabla "USUARIO_D" en la base de datos y
 * se utiliza para registrar y transferir informacion de los usuarios.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "USUARIO_D")
@Schema(
        name="Resena",
        description = "microservicio capaz de obtener las resenas de los usuarios"
)
public class User {

    /**
     * Identificador unico del usuario.
     * se genera de forma automatica por la base de datos
     */
    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario
     * Este campo es obligatorio
     */
    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name = "nombre")
    private String userName;

    /**
     * Gmail del usuario
     * Este campo es obligatorio
     */
    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @NotBlank(message= "Se debe ingresar un gmail")
    @Column(name = "gmail")
    private String gmail;

    /**
     * Lista de resenas de un disco
     */
    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Resena> resenaList = new ArrayList<>();

}
