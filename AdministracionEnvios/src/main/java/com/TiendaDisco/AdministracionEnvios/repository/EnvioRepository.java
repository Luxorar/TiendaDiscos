package com.TiendaDisco.AdministracionEnvios.repository;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * Repositorio JPA para la entidad {@link Envio}.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface EnvioRepository extends JpaRepository<Envio, Long> {

}
