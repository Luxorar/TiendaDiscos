package com.TiendaDisco.ManejoStock.repository;

import com.TiendaDisco.ManejoStock.model.infoStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoStockRepository extends JpaRepository<infoStock, Long> {
}