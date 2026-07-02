package com.TiendaDisco.AdministracionUsuario.repository;

import com.TiendaDisco.AdministracionUsuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link User}.
 * <p>Proporciona metodos de acceso a datos para la tabla de usuarios.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param Username nombre de usuario a buscar
     * @return {@link Optional} con el {@link User} si existe
     */
    Optional<User> findByUserName(String Username);
}
