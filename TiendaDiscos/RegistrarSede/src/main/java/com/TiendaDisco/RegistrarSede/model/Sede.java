package com.TiendaDisco.RegistrarSede.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany @JoinTable(
            name= "SEDE_PRODUCTOS",
            joinColumns = @JoinColumn(name = "sede_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> listProducto = new ArrayList<>();

    @JsonIgnore
    @ManyToMany @JoinTable(
            name= "SEDE_DISCOS",
            joinColumns = @JoinColumn(name = "sede_id"),
            inverseJoinColumns = @JoinColumn(name = "disco_id")
    )
    private List<Disco> listDisco = new ArrayList<>();
}
