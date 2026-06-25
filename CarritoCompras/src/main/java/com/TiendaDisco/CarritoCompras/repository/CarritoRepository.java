package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito,Long> {

    Optional<Carrito> findByUserId(Long userId);
}
