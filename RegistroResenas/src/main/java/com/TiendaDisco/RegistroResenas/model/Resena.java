package com.TiendaDisco.RegistroResenas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Entidad que representa una reseña de un disco.
 * Esta clase esta mapeada a la tabla "RESENA" en la base de datos y
 * se utiliza para registrar y transferir informacion de las resenas.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "RESENA")
@Schema(
        name="Resena",
        description = "microservicio capaz de obtener las resenas de los usuarios"
)
public class Resena {

    /**
     * Identificador unico de la resena.
     * se genera de forma automatica por la base de datos
     */
    @Schema(
            title="id",
            description="Identificador unico para las reseñas",
            example="1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario dueño de la resena
     * Este campo es obligatorio
     */
    @Schema(
            title="Users",
            description = "Indica al usuario dueño de la resena",
            example="Marcelo"
    )
    @NotNull(message= "Se debe ingresar un User")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Disco relacionado a la resena
     * Este campo es obligatorio
     */
    @Schema(
            title="Disco",
            description = "Indica el disco donde proviene de la resena",
            example="Bad"
    )
    @NotNull(message= "Se debe ingresar un disco")
    @ManyToOne
    @JoinColumn(name = "disco_id")
    private Disco disco;

    /**
     * Reseña guardada como un mensaje
     * Este campo es obligatorio
     */
    @Schema(
            title="Mensaje",
            description = "Es el mensaje de la reseña",
            example="Muy buen disco 10/10"
    )
    @NotBlank(message= "Se debe ingresar un mensaje")
    @Column(name = "mensaje")
    private String mensaje;
}
