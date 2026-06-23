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

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ElementCollection
    @CollectionTable(name = "DESCUENTO_DISCOS", joinColumns = @JoinColumn(name = "descuento_id"))
    @Column(name = "disco_id")
    private List<Long> discoIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "DESCUENTO_PRODUCTOS", joinColumns = @JoinColumn(name = "descuento_id"))
    @Column(name = "producto_id")
    private List<Long> productoIds = new ArrayList<>();

    @Column(name = "descuento")
    private double descuento;
}