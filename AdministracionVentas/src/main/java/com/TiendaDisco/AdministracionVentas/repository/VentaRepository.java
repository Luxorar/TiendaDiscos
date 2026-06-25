package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByUsuario(Long usuarioId);

    @Query("SELECT v FROM Venta v WHERE " +
           "(:fechaInicio IS NULL OR v.fechaVenta >= :fechaInicio) AND " +
           "(:fechaFin IS NULL OR v.fechaVenta <= :fechaFin) AND " +
           "(:usuarioId IS NULL OR v.usuario = :usuarioId)")
    List<Venta> findWithFilters(@Param("fechaInicio") LocalDate fechaInicio,
                                @Param("fechaFin") LocalDate fechaFin,
                                @Param("usuarioId") Long usuarioId);
}
