package com.TiendaDisco.RegistrarSede.mapper;

import com.TiendaDisco.RegistrarSede.dto.DiscoDTO;
import com.TiendaDisco.RegistrarSede.dto.ProductoDTO;
import com.TiendaDisco.RegistrarSede.dto.SedeDTO;
import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.model.Sede;

/**
 * Clase utilitaria encargada de transformar las entidades de dominio del microservicio
 * de Gestión de Sedes en Objetos de Transferencia de Datos (DTO).
 * Permite exponer la información de sucursales, discos y productos de forma segura.
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public class Mapper {

    /**
     * Convierte una entidad {@link Sede} a su representación pública {@link SedeDTO}.
     * Extrae los datos principales de la sucursal física, incluyendo su ubicación y contacto.
     * * @param s La entidad sede proveniente de la base de datos.
     * @return Un objeto {@link SedeDTO} instanciado a través del patrón Builder,
     * o {@code null} si el parámetro de entrada es nulo.
     */
    public static SedeDTO toDTO(Sede s) {
        if (s == null) return null;
        return SedeDTO.builder()
                .id(s.getId())
                .nombreSede(s.getNombreSede())
                .direccionSede(s.getDireccionSede())
                .numberSedeTelefono(s.getNumberSedeTelefono())
                .build();
    }

    /**
     * Convierte una entidad {@link Disco} a su representación simplificada {@link DiscoDTO}.
     * En el contexto del microservicio de Sedes, se utiliza para listar los discos
     * disponibles en una sucursal específica.
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
                .precio(d.getPrecio())
                .build();
    }

    /**
     * Convierte una entidad {@link Producto} a su representación {@link ProductoDTO}.
     * Se utiliza para mapear otros artículos o mercancía general (merchandising)
     * que se vende en la sede, aislando la información crítica.
     * * @param p La entidad producto proveniente de la base de datos.
     * @return Un objeto {@link ProductoDTO} instanciado a través del patrón Builder,
     * o {@code null} si el parámetro de entrada es nulo.
     */
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;
        return ProductoDTO.builder()
                .id(p.getId())
                .nombreProducto(p.getNombreProducto())
                .precio(p.getPrecio())
                .build();
    }

}
