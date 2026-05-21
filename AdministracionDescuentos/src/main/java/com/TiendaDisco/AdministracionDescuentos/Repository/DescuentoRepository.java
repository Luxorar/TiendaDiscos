package com.TiendaDisco.AdministracionDescuentos.Repository;

import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    Optional<Descuento> findByNombre(String nombre);
}