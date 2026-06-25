package com.TiendaDisco.AdministracionEnvios.mapper;

import com.TiendaDisco.AdministracionEnvios.DTO.EnvioDTO;
import com.TiendaDisco.AdministracionEnvios.client.VentaClient;
import com.TiendaDisco.AdministracionEnvios.model.Envio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private VentaClient ventaClient;

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
