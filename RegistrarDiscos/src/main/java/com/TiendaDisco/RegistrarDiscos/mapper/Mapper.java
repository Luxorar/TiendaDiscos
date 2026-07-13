package com.TiendaDisco.RegistrarDiscos.mapper;

import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;

import java.util.Collections;
import java.util.List;

/**
 * Clase utilitaria encargada de realizar el mapeo y conversión de datos
 * entre las entidades de dominio y los Objetos de Transferencia de Datos (DTO).
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class Mapper {

    /**
     * Convierte una entidad {@link Disco} a su representación segura {@link DiscoDTO}.
     * Este metodo gestiona de forma segura los valores nulos y procesa la lista de
     * entidades asociadas (títulos) para extraer únicamente su representación en texto.
     * * @param disco La entidad de base de datos que contiene toda la información del disco.
     * @return Un objeto {@link DiscoDTO} instanciado a través del patrón Builder,
     * o {@code null} si el parámetro de entrada es nulo.
     */
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
                .imagen(disco.getImagen())
                .titulos(titulos)
                .build();
    }
}
