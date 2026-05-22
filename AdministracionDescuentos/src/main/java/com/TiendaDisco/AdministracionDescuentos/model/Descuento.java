package com.TiendaDisco.AdministracionDescuentos.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name="DESCUENTO")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese nombre del descuento")
    @Column(name = "nombre_descuento")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "disco")
    private Disco disco;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ManyToMany
    @JoinTable(
            name = "DESCUENTOS_DISCO",
            joinColumns = @JoinColumn(name = "descuento_id"),
            inverseJoinColumns = @JoinColumn(name = "disco_id")
    )
    private List<Disco> discosAgregados = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "DESCUENTOS_PRODUCTOS",
            joinColumns = @JoinColumn(name = "descuento_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productosAgregados = new ArrayList<>();

    @Column(name = "descuento")
    private double descuento;
}