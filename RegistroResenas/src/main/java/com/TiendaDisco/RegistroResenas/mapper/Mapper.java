package com.TiendaDisco.RegistroResenas.mapper;

import com.TiendaDisco.RegistroResenas.DTO.DiscoDTO;
import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.model.User;

/**
 * Clase utilitaria encargada de transformar las entidades de dominio del microservicio
 * de Reseñas en Objetos de Transferencia de Datos (DTO).
 * Utiliza sobrecarga de métodos para centralizar la lógica de mapeo.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class Mapper {

    /**
     * Convierte una entidad {@link Resena} a su representación {@link ResenaDTO}.
     * Extrae de forma segura los nombres del usuario y del disco asociados,
     * previniendo excepciones (NullPointerException) si estas relaciones están vacías.
     * * @param r La entidad reseña proveniente de la base de datos.
     * @return Un objeto {@link ResenaDTO} construido con el patrón Builder,
     * o {@code null} si el parámetro de entrada es nulo.
     */
    public static ResenaDTO toDTO(Resena r) {
        if (r == null) return null;

        return ResenaDTO.builder()
                .id(r.getId())
                .userName(r.getUser() != null ? r.getUser().getUserName() : null)
                .nombreDisco(r.getDisco() != null ? r.getDisco().getNombreDisco() : null)
                .mensaje(r.getMensaje())
                .build();
    }

    /**
     * Convierte una entidad {@link User} a su representación pública {@link UserDTO}.
     * Se utiliza para enviar la información del usuario sin exponer datos sensibles.
     * * @param u La entidad usuario proveniente de la base de datos.
     * @return Un objeto {@link UserDTO} instanciado a través del patrón Builder,
     * o {@code null} si el parámetro de entrada es nulo.
     */
    public static UserDTO toDTO(User u) {
        if (u == null) return null;

        return UserDTO.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .gmail(u.getGmail())
                .build();
    }

    /**
     * Convierte una entidad {@link Disco} a su representación simplificada {@link DiscoDTO}.
     * En el contexto de este microservicio, extrae únicamente la información
     * básica del disco asociada a la reseña.
     * * @param d La entidad disco proveniente de la base de datos.
     * @return Un objeto {@link DiscoDTO} instanciado a través del patrón Builder,
     * o {@code null} si el parámetro de entrada es nulo.
     */
    public static DiscoDTO toDTO(Disco d) {
        if (d == null) return null;

        return DiscoDTO.builder()
                .id(d.getId())
                .nombreDisco(d.getNombreDisco())
                .artista(d.getArtista())
                .build();
    }
}
