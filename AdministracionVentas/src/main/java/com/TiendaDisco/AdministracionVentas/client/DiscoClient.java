package com.TiendaDisco.AdministracionVentas.client;

import com.TiendaDisco.AdministracionVentas.dto.DiscoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DiscoClient", url= "http://registrar-discos:8086")
public interface DiscoClient {

    @GetMapping("/api/v1/discos/{id}")
    ResponseEntity<DiscoDTO> obtenerDiscoPorId(@PathVariable Long id);

}
