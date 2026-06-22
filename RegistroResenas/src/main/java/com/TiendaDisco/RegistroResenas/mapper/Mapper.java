package com.TiendaDisco.RegistroResenas.mapper;

import com.TiendaDisco.RegistroResenas.DTO.DiscoDTO;
import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.DTO.UserDTO;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.model.User;

public class Mapper {

    public static ResenaDTO toDTO(Resena r) {
        if (r == null) return null;

        return ResenaDTO.builder()
                .id(r.getId())
                .userName(r.getUser() != null ? r.getUser().getUserName() : null)
                .nombreDisco(r.getDisco() != null ? r.getDisco().getNombreDisco() : null)
                .mensaje(r.getMensaje())
                .build();
    }

    public static UserDTO toDTO(User u) {
        if (u == null) return null;

        return UserDTO.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .gmail(u.getGmail())
                .build();
    }

    public static DiscoDTO toDTO(Disco d) {
        if (d == null) return null;

        return DiscoDTO.builder()
                .id(d.getId())
                .nombreDisco(d.getNombreDisco())
                .artista(d.getArtista())
                .build();
    }
}
