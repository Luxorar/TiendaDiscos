package com.TiendaDisco.AdministracionVentas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "USUARIO")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message= "Se debe ingresar un nombre") @Column(name = "user_name") String userName;
    @NotBlank(message= "Se debe ingresar un gmail") @Column(name = "gmail") String gmail;
}
