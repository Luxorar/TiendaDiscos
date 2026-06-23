package com.TiendaDisco.AdministracionUsuario.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "USUARIO")
@Schema(
        title = "Users",
        description = "Microservicio dedicado a los usuarios"
)
public class User {

    @Schema(
            title = "id",
            description = "Identificador unico para los usuarios",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            title = "Nombre de usuarip",
            description = "Nombre de los usuarios",
            example = "Juanitopro123"
    )
    @NotBlank(message = "Ingreso de nombre de usuario obligatorio")
    @Column(name = "nombre")
    private String userName;

    @Schema(
            title = "Gmail",
            description = "Gmail asociado al usuario",
            example = "juanitolocal@example.com"
    )
    @NotBlank(message = "Ingreso de gmail obligatorio")
    @Column(name = "gmail")
    private String gmail;

    @Schema(
            title = "Fecha de registro",
            description = "Fecha de registro asociada al usuario",
            example = "20/1/26"
    )
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Schema(
            title = "Puntos",
            description = "Puntos asociados a los usuarios",
            example = "200"
    )
    @Column(name = "puntos")
    private Integer puntos;

    @Schema(
            title = "Contrasena",
            description = "Contraseña asociada al usuario",
            example = "contrasena100porcientosegura"
    )
    @NotNull(message = "su cuenta necesita una contraseña")
    @Column(name = "contrasena")
    private String contrasena;

    @Schema(
            title = "Actividad",
            description = "Identifica si el usuario esta activo en su cuenta",
            example = "True"
    )
    @Column(name = "cuenta_activa")
    private Boolean cuentaActiva;
}
