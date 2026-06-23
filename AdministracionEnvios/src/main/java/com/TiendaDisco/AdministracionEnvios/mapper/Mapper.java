package com.TiendaDisco.AdministracionEnvios.mapper;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.model.Envio;

public class Mapper {

    public static EnvioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        return EnvioDTO.builder()
                .id(envio.getId())
                .ventaId(envio.getVentaId())
                .direccionDestino(envio.getDireccionDestino())
                .tipoDespacho(envio.getTipoDespacho())
                .empresaReparto(envio.getEmpresaReparto())
                .estadoEnvio(envio.getEstadoEnvio())
                .fechaEntrega(envio.getFechaEntrega())
                .build();
    }
}
