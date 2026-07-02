package com.TiendaDisco.AdministracionUsuario.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


/**
 * Entidad que representa un administrador del sistema.
 * <p>Almacena la informacion personal y de cuenta del administrador,
 * incluyendo nombre, correo y estado de la cuenta.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "ADMINISTRADOR")
@Schema(
        name="Administrador",
        description="Microservicio modelado a administradores"
)
public class Admin {

    @Schema(
            title = "id",
            description = "Identificador unico para los administradores",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            title = "Nombre Admin",
            description = "Nombre del administrador",
            example = "Administrador 1"
    )
    @NotBlank(message = "Ingreso de nombre de usuario obligatorio")
    @Column(name = "nombre")
    private String userName;

    @Schema(
            title = "Gmail",
            description = "Gmail del administrador",
            example = "administrador1@example.com"
    )
    @NotBlank(message = "Ingreso de gmail obligatorio")
    @Column(name = "gmail")
    private String gmail;

    @Schema(
            title = "Fecha de registro",
            description = "Indica la fecha de registro del administrador",
            example = "20/01/2026"
    )
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Schema(
            title = "Contrasena",
            description = "Contraseña del administrador",
            example = "contrasegura123"
    )
    @Column(name = "contrasena")
    @NotNull(message = "su cuenta necesita una contraseña")
    private String contrasena;

    @Schema(
            title = "Actividad",
            description = "Identifica si el administrador esta activo en su cuenta",
            example = "True"
    )
    @NotNull(message = "Ingrese un boolean en cuenta activa")
    @Column(name = "cuenta_activa")
    private Boolean cuentaActiva;
}
