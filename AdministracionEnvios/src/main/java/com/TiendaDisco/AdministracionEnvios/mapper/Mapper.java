package com.TiendaDisco.AdministracionEnvios.mapper;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.client.VentaClient;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase utilitaria para mapear entidades de envio a DTOs.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Component
public class Mapper {
    @Autowired
    private VentaClient ventaClient;

    /**
     * Convierte una entidad {@link Envio} a su DTO, consultando los
     * datos de la venta asociada mediante Feign client.
     *
     * @param envio entidad Envio a convertir, puede ser {@code null}
     * @return {@link EnvioDTO} con los datos mapeados, o {@code null} si la entrada es {@code null}
     */
    public EnvioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        return EnvioDTO.builder()
                .id(envio.getId())
                .ventaId(ventaClient.getVentaId(envio.getVentaId()).getBody())
                .direccionDestino(envio.getDireccionDestino())
                .tipoDespacho(envio.getTipoDespacho())
                .empresaReparto(envio.getEmpresaReparto())
                .estadoEnvio(envio.getEstadoEnvio())
                .fechaEntrega(envio.getFechaEntrega())
                .build();
    }
}
