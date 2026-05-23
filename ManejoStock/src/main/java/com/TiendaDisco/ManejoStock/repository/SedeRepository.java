package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.Sede;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {
    Optional<Sede> findByNombreSede(String nombreSede);
}