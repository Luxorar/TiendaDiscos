package com.TiendaDisco.AdministracionVentas.repository;

import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Venta}.
 * <p>Proporciona metodos de acceso a datos para la tabla de ventas,
 * incluyendo consultas personalizadas con filtros.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {

    /**
     * Busca ventas por identificador de usuario.
     *
     * @param usuarioId identificador del usuario
     * @return lista de {@link Venta} del usuario
     */
    List<Venta> findByUsuario(Long usuarioId);

    /**
     * Busca ventas aplicando filtros opcionales por fecha y usuario.
     *
     * @param fechaInicio fecha minima de la venta (opcional)
     * @param fechaFin    fecha maxima de la venta (opcional)
     * @param usuarioId   identificador del usuario (opcional)
     * @return lista de {@link Venta} filtrada
     */
    @Query("SELECT v FROM Venta v WHERE " +
           "(:fechaInicio IS NULL OR v.fechaVenta >= :fechaInicio) AND " +
           "(:fechaFin IS NULL OR v.fechaVenta <= :fechaFin) AND " +
           "(:usuarioId IS NULL OR v.usuario = :usuarioId)")
    List<Venta> findWithFilters(@Param("fechaInicio") LocalDate fechaInicio,
                                @Param("fechaFin") LocalDate fechaFin,
                                @Param("usuarioId") Long usuarioId);
}
