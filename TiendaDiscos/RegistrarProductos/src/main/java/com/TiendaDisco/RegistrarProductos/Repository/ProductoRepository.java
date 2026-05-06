package com.TiendaDisco.RegistrarProductos.Repository;

import com.TiendaDisco.RegistrarProductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
