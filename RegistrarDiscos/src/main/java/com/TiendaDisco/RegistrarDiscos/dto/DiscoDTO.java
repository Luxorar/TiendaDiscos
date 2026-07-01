package com.TiendaDisco.RegistrarDiscos.dto;

import lombok.*;

import java.util.List;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información
 * simplificada y pública de un disco.
 * Se utiliza para transferir información hacia la capa de presentación sin exponer la entidad de base de datos.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class DiscoDTO {
    /**
     * Identificador único del disco.
     */
    private Long id;

    /**
     * Nombre o título oficial del álbum/disco.
     */
    private String nombreDisco;

    /**
     * Nombre del artista, banda o grupo musical creador del disco.
     */
    private String artista;

    /**
     * Precio de venta asignado al disco en la tienda.
     */
    private Integer precio;

    /**
     * Lista de titulos.
     */
    private List<String> titulos;
}
