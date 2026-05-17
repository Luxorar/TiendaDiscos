package com.TiendaDisco.AdministracionUsuario.mapper;

import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.User;

public class Mapper {

    public static UserDTO toDTO(User u){
        if(u==null)return null;

        return UserDTO.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .fechaRegistro(u.getFechaRegistro())
                .puntos(u.getPuntos())
                .build();
    }

}
