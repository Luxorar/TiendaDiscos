package com.TiendaDisco.RegistrarSede.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información
 * simplificada y pública de una sede.
 * Se utiliza para transferir información hacia la capa de presentación sin exponer la entidad de base de datos.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {

    /**
     * Identificador único del disco.
     */
    private Long id;

    /**
     * Nombre sede.
     */
    private String nombreSede;

    /**
     * Direccion de la sede.
     */
    private String direccionSede;

    /**
     * Numero telefonico de la sede.
     */
    private String numberSedeTelefono;
}
