package com.TiendaDisco.RegistrarSede.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Builder
@Table(name="SEDES")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nombre de Sede obligatorio")
    @Column(name="nombre")
    private String nombreSede;

    @NotBlank(message = "dirección de Sede obligatorio")
    @Column(name="direccion")
    private String direccionSede;

    @NotBlank(message = "numero de Sede obligatorio")
    @Column(name="numero_sede")
    private String numberSedeTelefono;

    @JoinColumn(name="productos_id")
    @ManyToOne
    private Producto producto;

    @JoinColumn(name="discos_id")
    @ManyToOne
    private Disco disco;
}
