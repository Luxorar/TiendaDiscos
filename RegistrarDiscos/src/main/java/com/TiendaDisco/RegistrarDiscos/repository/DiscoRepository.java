package com.TiendaDisco.RegistrarDiscos.repository;

import com.TiendaDisco.RegistrarDiscos.model.Disco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {

    @Query("SELECT d FROM Disco d WHERE LOWER(d.nombreDisco) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(d.artista) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Disco> search(@Param("query") String query);
}