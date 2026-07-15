package com.TiendaDisco.AdministracionUsuario.repository;

import com.TiendaDisco.AdministracionUsuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String Username);
    Optional<User> findByGmail(String gmail);
}
