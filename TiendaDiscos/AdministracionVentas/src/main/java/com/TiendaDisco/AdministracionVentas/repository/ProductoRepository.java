package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
