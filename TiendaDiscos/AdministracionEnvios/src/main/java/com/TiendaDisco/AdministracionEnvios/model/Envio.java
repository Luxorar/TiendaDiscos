package com.TiendaDisco.AdministracionEnvios.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    private Long id;
    @NotNull(message = "Ingreso de id de venta obligatorio") private Long ventaId;
    @NotBlank(message = "Ingreso de direccion de envio obligatorio") private String direccionDestino;
    @NotNull(message = "Ingreso de tipo de despacho obligatorio") private TipoDespacho tipoDespacho;
    @NotBlank(message = "Ingreso de empresa de reparto obligatorio") private String empresaReparto;
    @NotNull(message = "Ingreso de estado de envio obligatorio") private EstadoEnvio estadoEnvio;
    @NotNull(message = "Ingreso de fecha de entrega obligatorio") private Date fechaEntrega;





}
