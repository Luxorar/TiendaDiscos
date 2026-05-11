package com.TiendaDisco.RegistrarDiscos.Repository;

import com.TiendaDisco.RegistrarDiscos.model.Disco;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiscoRepository extends JpaRepository<Disco,Long>{

}