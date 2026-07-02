package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Carrito}.
 * <p>Proporciona metodos de acceso a datos para la tabla de carritos.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface CarritoRepository extends JpaRepository<Carrito,Long> {

    /**
     * Busca un carrito por el identificador del usuario.
     *
     * @param userId identificador del usuario
     * @return {@link Optional} con el {@link Carrito} si existe
     */
    Optional<Carrito> findByUserId(Long userId);
}
