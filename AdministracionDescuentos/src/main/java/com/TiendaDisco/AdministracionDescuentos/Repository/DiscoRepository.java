package com.TiendaDisco.AdministracionDescuentos.Repository;

import com.TiendaDisco.AdministracionDescuentos.model.Disco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {
}