package com.TiendaDisco.RegistrarDiscos.mapper;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;

public class Mapper {
    public static DiscoDTO toDTO(Disco disco) {
        if (disco == null) return null;
        return DiscoDTO.builder()
                .id(disco.getId())
                .nombreDisco(disco.getNombreDisco())
                .artista(disco.getArtista())
                .precio(disco.getPrecio())
                .titulo(disco.getTitulo() != null ? disco.getTitulo().getTitulo() : null)
                .build();
    }
}
