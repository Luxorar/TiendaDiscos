package com.TiendaDisco.RegistrarDiscos.repository;

import com.TiendaDisco.RegistrarDiscos.model.Disco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {
}