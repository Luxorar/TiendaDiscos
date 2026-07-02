package com.TiendaDisco.AdministracionDescuentos.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que transporta los datos de un disco.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DiscoDTO {
    private Long id;

    private String nombreDisco;

    private String artista;

    private int precio;
}
