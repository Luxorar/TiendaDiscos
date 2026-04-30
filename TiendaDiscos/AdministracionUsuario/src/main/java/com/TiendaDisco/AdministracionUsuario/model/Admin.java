package com.TiendaDisco.AdministracionUsuario.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Long id;
    @NotBlank(message = "Ingreso de nombre de usuario obligatorio")
    private String userName;
    @NotBlank(message = "Ingreso de gmail obligatorio")
    private String gmail;
    private Date fechaRegistro;
    private String contraseña;
    @NotNull(message = "Ingrese un boolean en estadoCuenta")
    private Boolean estadoCuenta;
}
