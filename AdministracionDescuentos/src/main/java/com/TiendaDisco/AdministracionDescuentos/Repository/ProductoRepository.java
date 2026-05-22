package com.TiendaDisco.AdministracionDescuentos.Repository;

import com.TiendaDisco.AdministracionDescuentos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}