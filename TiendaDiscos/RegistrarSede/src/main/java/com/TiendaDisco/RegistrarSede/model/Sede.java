package com.TiendaDisco.RegistrarSede.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="SEDE")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nombre de Sede obligatorio")
    @Column(name="Nombre")
    private String nombreSede;

    @NotBlank(message = "dirección de Sede obligatorio")
    @Column(name="Direccion")
    private String direccionSede;

    @NotBlank(message = "numero de Sede obligatorio")
    @Column(name="Numero_Sede")
    private String numberSedeTelefono;

    @JoinColumn(name="Producto_Id")
    @ManyToOne
    private Producto producto;

    @JoinColumn(name="Disco_Id")
    @ManyToOne
    private Disco disco;
}
