package com.TiendaDisco.CarritoCompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name = "USUARIO_C")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank(message= "Se debe ingresar un nombre") String userName;

    @Column(name = "gmail")
    @NotBlank(message= "Se debe ingresar un gmail") String gmail;

    @Column(name = "contraseña")
    @NotBlank(message= "Se debe ingresar una contraseña") String password;

    @OneToOne(mappedBy = "user") private Carrito carrito;
}
