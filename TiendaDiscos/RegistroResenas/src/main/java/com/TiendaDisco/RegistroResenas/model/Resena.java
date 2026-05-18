package com.TiendaDisco.RegistroResenas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @Entity
@Table(name = "RESENA")

public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Se debe ingresar un User")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message= "Se debe ingresar un disco")
    @ManyToOne
    @JoinColumn(name = "disco_id")
    private Disco disco;

    @NotBlank(message= "Se debe ingresar un mensaje")
    @Column(name = "mensaje")
    private String mensaje;
}
