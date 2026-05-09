package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
