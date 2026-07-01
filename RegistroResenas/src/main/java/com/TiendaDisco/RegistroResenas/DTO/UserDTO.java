package com.TiendaDisco.RegistroResenas.DTO;

import lombok.*;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información
 * simplificada y pública de los usuarios.
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
public class UserDTO {

    /**
     * Identificador único del disco.
     */
    private Long id;

    /**
     * Nombre de usuario.
     */
    private String userName;

    /**
     * Gmail del usuario.
     */
    private String gmail;
}
