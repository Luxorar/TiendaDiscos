package com.TiendaDisco.RegistrarProductos.repository;

import com.TiendaDisco.RegistrarProductos.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByMarca(String marca);
}