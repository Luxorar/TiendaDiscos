package com.TiendaDisco.RegistrarSede.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Entidad que representa una sede.
 * Esta clase esta mapeada a la tabla "SEDES" en la base de datos y
 * se utiliza para registrar y transferir informacion de las sedes.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name="SEDES")
public class Sede {

    /**
     * Identificador unico de la sede.
     * se genera de forma automatica por la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la sede
     * Este campo es obligatorio
     */
    @NotBlank(message = "nombre de Sede obligatorio")
    @Column(name="nombre")
    private String nombreSede;

    /**
     * Direccion de la sede
     * Este campo es obligatorio
     */
    @NotBlank(message = "dirección de Sede obligatorio")
    @Column(name="direccion")
    private String direccionSede;

    /**
     * Numero telefonico de la sede
     * Este campo es obligatorio
     */
    @NotBlank(message = "numero de Sede obligatorio")
    @Column(name="numero_sede")
    private String numberSedeTelefono;
}
