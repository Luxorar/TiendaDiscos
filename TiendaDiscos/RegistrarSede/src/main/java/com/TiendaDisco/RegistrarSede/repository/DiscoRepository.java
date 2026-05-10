package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DiscoRepository extends JpaRepository<Disco, Long> {

}
