package com.TiendaDisco.AdministracionDescuentos.Repository;

import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Descuento}.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    /**
     * Busca un descuento por su nombre.
     *
     * @param nombre nombre del descuento
     * @return {@link Optional} con el {@link Descuento} si existe
     */
    Optional<Descuento> findByNombre(String nombre);
}