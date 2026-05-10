package com.TiendaDisco.RegistrarSede.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Se debe ingresar un nombre")
    @Column(name="Nombre")  String nombreProducto;

    @NotNull(message= "Se debe ingresar un precio")
    @Column(name="Precio") int precio;

    @OneToMany(mappedBy = "Producto")
    private List<Sede> sedeList=new ArrayList<>();
}
