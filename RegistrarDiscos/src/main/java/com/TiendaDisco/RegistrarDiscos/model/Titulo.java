package com.TiendaDisco.RegistrarDiscos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Entidad que representa la lista de titulos en un disco.
 * Esta clase esta mapeada a la tabla "TITULO" en la base de datos y
 * se utiliza para obtener y registrar las canciones de los discos.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "TITULO")
public class Titulo {

    /**
     * Identificador unico del titulo.
     * se genera de forma automatica por la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la cancion
     * Este campo es obligatorio
     */
    @NotBlank(message = "Se debe ingresar un titulo")
    @Column(name = "titulo")
    private String titulo;

    /**
     * Relacion inversa hacia el disco
     * al que pertenece la cancion.
     */
    @ManyToOne
    @JoinColumn(name = "disco_id")
    private Disco disco;
}