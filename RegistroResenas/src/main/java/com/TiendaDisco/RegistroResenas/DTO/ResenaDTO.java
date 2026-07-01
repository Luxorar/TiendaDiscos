package com.TiendaDisco.RegistroResenas.DTO;

import lombok.*;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información
 * simplificada y pública de una reseña.
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
public class ResenaDTO {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre de usuario.
     */
    private String userName;

    /**
     * Nombre del disco de la reseña.
     */
    private String nombreDisco;

    /**
     * Mensaje de la reseña.
     */
    private String mensaje;
}
