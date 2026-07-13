package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.CarritoDisco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoDiscoRepository extends JpaRepository<CarritoDisco, Long> {
    List<CarritoDisco> findByCarritoUserId(Long userId);
    Optional<CarritoDisco> findByCarritoUserIdAndDiscoId(Long userId, Long discoId);
    void deleteByCarritoUserId(Long userId);
}
