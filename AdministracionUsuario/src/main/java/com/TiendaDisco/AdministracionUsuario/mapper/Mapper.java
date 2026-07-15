package com.TiendaDisco.AdministracionUsuario.mapper;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;

public class Mapper {

    public static UserDTO toDTO(User u){
        if(u==null)return null;

        return UserDTO.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .gmail(u.getGmail())
                .fechaRegistro(u.getFechaRegistro())
                .puntos(u.getPuntos())
                .credito(u.getCredito())
                .modoOscuro(u.getModoOscuro())
                .direccionPredeterminada(u.getDireccionPredeterminada())
                .telefono(u.getTelefono())
                .build();
    }

    public static AdminDTO toDTO(Admin a){
        if(a==null)return null;

        return AdminDTO.builder()
                .id(a.getId())
                .userName(a.getUserName())
                .fechaRegistro(a.getFechaRegistro())
                .build();
    }

}
