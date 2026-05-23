package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}