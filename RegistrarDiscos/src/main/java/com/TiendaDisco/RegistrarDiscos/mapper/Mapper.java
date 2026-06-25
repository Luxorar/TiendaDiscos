package com.TiendaDisco.RegistrarDiscos.mapper;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;

import java.util.Collections;
import java.util.List;

public class Mapper {
    public static DiscoDTO toDTO(Disco disco) {
        if (disco == null) return null;
        List<String> titulos = disco.getTitulos() != null
                ? disco.getTitulos().stream().map(Titulo::getTitulo).toList()
                : Collections.emptyList();
        return DiscoDTO.builder()
                .id(disco.getId())
                .nombreDisco(disco.getNombreDisco())
                .artista(disco.getArtista())
                .precio(disco.getPrecio())
                .titulos(titulos)
                .build();
    }
}
