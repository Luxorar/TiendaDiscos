package com.TiendaDisco.AdministracionEnvios.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name="Envios",
        description = "Microservicio capaz de gestionar envios"
)
public class Envio {

    @Schema(
            name="id",
            description = "Identificador unico del envio",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            name= "Venta id",
            description = "Obtiene el id de una venta",
            example = "1"
    )
    @NotNull(message = "Ingreso de id de venta obligatorio")
    @Column(name = "venta_id") private Long ventaId;

    @Schema(
            name="Direccion destino",
            description = "Identifica la direccion del destinatario del envio",
            example = "Santa rosa 567"
    )
    @NotBlank(message = "Ingreso de direccion de envio obligatorio")
    @Column(name = "direccion_destino") private String direccionDestino;

    @Schema(
            name="Tipo despacho",
            description = "Indica el tipo del despacho",
            example = "RETIRO_TIENDA"
    )
    @NotNull(message = "Ingreso de tipo de despacho obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_despacho") private TipoDespacho tipoDespacho;

    @Schema(
            name="Empresa reparto",
            description = "Identifica la empresa de reparto",
            example = "Chilexpress"
    )
    @NotBlank(message = "Ingreso de empresa de reparto obligatorio")
    @Column(name = "empresa_reparto") private String empresaReparto;

    @Schema(
            name="Estado envio",
            description = "Identificador del estado del envio",
            example = "Entregado"
    )
    @NotNull(message = "Ingreso de estado de envio obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_envio")private EstadoEnvio estadoEnvio;

    @Schema(
            name="fecha de entrega",
            description = "Indica la fecha aproximada de llegada del envio",
            example = "18/06/2026"
    )
    @NotNull(message = "Ingreso de fecha de entrega obligatorio")
    @Column(name = "fecha_entrega")private LocalDate fechaEntrega;

}
