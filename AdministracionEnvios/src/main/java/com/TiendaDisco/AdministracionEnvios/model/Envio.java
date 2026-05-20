package com.TiendaDisco.AdministracionEnvios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "ENVIO")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingreso de id de venta obligatorio")
    @Column(name = "venta_id") private Long ventaId;

    @NotBlank(message = "Ingreso de direccion de envio obligatorio")
    @Column(name = "direccion_destino") private String direccionDestino;

    @NotNull(message = "Ingreso de tipo de despacho obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_despacho") private TipoDespacho tipoDespacho;

    @NotBlank(message = "Ingreso de empresa de reparto obligatorio")
    @Column(name = "empresa_reparto") private String empresaReparto;

    @NotNull(message = "Ingreso de estado de envio obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_envio")private EstadoEnvio estadoEnvio;

    @NotNull(message = "Ingreso de fecha de entrega obligatorio")
    @Column(name = "fecha_entrega")private LocalDate fechaEntrega;

}
