package com.TiendaDisco.AdministracionUsuario.repository;

import com.TiendaDisco.AdministracionUsuario.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Admin}.
 * <p>Proporciona metodos de acceso a datos para la tabla de administradores.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Busca un administrador por su nombre de usuario.
     *
     * @param userName nombre del administrador a buscar
     * @return {@link Optional} con el {@link Admin} si existe
     */
    Optional<Admin> findByUserName(String userName);
}
