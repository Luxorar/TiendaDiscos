package com.TiendaDisco.AdministracionEnvios.DTO;

import com.TiendaDisco.AdministracionEnvios.model.EstadoEnvio;
import com.TiendaDisco.AdministracionEnvios.model.TipoDespacho;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnvioDTO {
    private Long id;
    private VentaDTO ventaId;
    private String direccionDestino;
    private TipoDespacho tipoDespacho;
    private String empresaReparto;
    private EstadoEnvio estadoEnvio;
    private LocalDate fechaEntrega;
}
