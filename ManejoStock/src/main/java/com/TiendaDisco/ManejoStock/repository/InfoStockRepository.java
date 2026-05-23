package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.infoStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoStockRepository extends JpaRepository<infoStock, Long> {
    List<infoStock> findBySede_NombreSede(String nombreSede);
    Optional<infoStock> findByNombreProducto(String nombreProducto);
}