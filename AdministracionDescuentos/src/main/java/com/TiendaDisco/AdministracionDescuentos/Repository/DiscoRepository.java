package com.TiendaDisco.AdministracionDescuentos.Repository;

import com.TiendaDisco.AdministracionDescuentos.model.Disco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoRepository extends JpaRepository<Disco, Long> {
}
