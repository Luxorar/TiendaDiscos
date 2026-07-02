package com.TiendaDisco.RegistrarDiscos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un disco en el catalogo.
 * Esta clase esta mapeada a la tabla "DISCOS" en la base de datos y
 * se utiliza para registrar y transferir informacion de los albumes.
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "DISCOS")
public class Disco {

    /**
     * Identificador unico del disco.
     * se genera de forma automatica por la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titulo oficial del disco
     * Este campo es obligatorio
     */
    @NotBlank(message = "Ingrese nombre del disco")
    @Column(name = "nombre")
    private String nombreDisco;

    /**
     * Nombre del grupo musical, banda o artista solista.
     * Este campo es obligatorio
     */
    @NotBlank(message = "Ingrese nombre del grupo o artista")
    @Column(name = "artista")
    private String artista;

    /**
     * Precio de venta del disco.
     * Se almacena como entero. Es obligatorio
     */
    @NotNull(message = "Ingrese el precio del disco")
    @Column(name = "precio")
    private Integer precio;

    /**
     * Lista de titulos que componen el disco.
     * Se ignora en la serializacion JSON para evitar
     * problemas de bucles infinitos al devolver respuestas REST.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "disco")
    private List<Titulo> titulos = new ArrayList<>();
}