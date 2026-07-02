package com.TiendaDisco.AdministracionUsuario.mapper;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;

/**
 * Clase utilitaria para mapear entidades del dominio a DTOs.
 * <p>Convierte entidades {@link User} y {@link Admin} a sus
 * correspondientes DTOs para la capa de presentacion.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class Mapper {

    /**
     * Convierte una entidad {@link User} a su DTO.
     *
     * @param u entidad User a convertir, puede ser {@code null}
     * @return {@link UserDTO} con los datos mapeados, o {@code null} si la entrada es {@code null}
     */
    public static UserDTO toDTO(User u){
        if(u==null)return null;

        return UserDTO.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .fechaRegistro(u.getFechaRegistro())
                .puntos(u.getPuntos())
                .build();
    }

    /**
     * Convierte una entidad {@link Admin} a su DTO.
     *
     * @param a entidad Admin a convertir, puede ser {@code null}
     * @return {@link AdminDTO} con los datos mapeados, o {@code null} si la entrada es {@code null}
     */
    public static AdminDTO toDTO(Admin a){
        if(a==null)return null;

        return AdminDTO.builder()
                .id(a.getId())
                .userName(a.getUserName())
                .fechaRegistro(a.getFechaRegistro())
                .build();
    }

}
