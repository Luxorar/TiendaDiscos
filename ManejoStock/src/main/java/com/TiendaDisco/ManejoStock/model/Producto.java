package com.TiendaDisco.ManejoStock.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.annotations.IdGeneratorType;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Entity
@Table(name="PRODUCTO_STOCK")
public class Producto extends infoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_producto")
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @Column(name = "id_producto")
    private Long idProducto;
}