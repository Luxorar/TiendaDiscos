package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito,Long> {

    Optional<Carrito> findByUserUserName(String userUserName);
}
