package com.TiendaDisco.AdministracionEnvios.repository;

import com.TiendaDisco.AdministracionEnvios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

}
