package com.TiendaDisco.AdministracionUsuario.repository;

import com.TiendaDisco.AdministracionUsuario.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
