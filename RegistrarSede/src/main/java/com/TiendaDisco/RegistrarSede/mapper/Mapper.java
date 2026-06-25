package com.TiendaDisco.RegistrarSede.mapper;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.dto.ProductoDTO;
import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.model.Sede;

public class Mapper {

    public static SedeDTO toDTO(Sede s) {
        if (s == null) return null;
        return SedeDTO.builder()
                .id(s.getId())
                .nombreSede(s.getNombreSede())
                .direccionSede(s.getDireccionSede())
                .numberSedeTelefono(s.getNumberSedeTelefono())
                .build();
    }

    public static DiscoDTO toDTO(Disco d) {
        if (d == null) return null;
        return DiscoDTO.builder()
                .id(d.getId())
                .nombreDisco(d.getNombreDisco())
                .artista(d.getArtista())
                .precio(d.getPrecio())
                .build();
    }

    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;
        return ProductoDTO.builder()
                .id(p.getId())
                .nombreProducto(p.getNombreProducto())
                .precio(p.getPrecio())
                .build();
    }

}
