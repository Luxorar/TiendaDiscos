package com.TiendaDisco.AdministracionUsuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "ADMINISTRADOR")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingreso de nombre de usuario obligatorio")
    @Column(name = "nombre")
    private String userName;

    @NotBlank(message = "Ingreso de gmail obligatorio")
    @Column(name = "gmail")
    private String gmail;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "contrasena")
    @NotNull(message = "su cuenta necesita una contraseña")
    private String contrasena;

    @NotNull(message = "Ingrese un boolean en cuenta activa")
    @Column(name = "cuenta_activa")
    private Boolean cuentaActiva;
}
