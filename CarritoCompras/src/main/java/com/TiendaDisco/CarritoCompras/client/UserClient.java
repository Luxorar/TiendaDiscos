package com.TiendaDisco.CarritoCompras.client;

import com.TiendaDisco.CarritoCompras.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para la comunicacion con el microservicio de Usuarios.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@FeignClient(name="UserClient", url="http://tiendadiscos-usuario:8081")
public interface UserClient {

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return {@link UserDTO} con los datos del usuario
     */
    @GetMapping("api/v1/admin/id/{id}")
    UserDTO getUserId(@PathVariable Long id);
}
