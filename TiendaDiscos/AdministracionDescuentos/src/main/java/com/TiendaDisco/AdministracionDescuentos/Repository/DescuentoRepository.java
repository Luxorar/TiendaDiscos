package com.TiendaDisco.AdministracionDescuentos.Repository;


import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

}