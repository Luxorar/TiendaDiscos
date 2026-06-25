package com.TiendaDisco.CarritoCompras.client;

import com.TiendaDisco.CarritoCompras.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="UserClient", url="http://tiendadiscos-usuario:8081")
public interface UserClient {

    @GetMapping("api/v1/admin/id/{id}")
    UserDTO getUserId(@PathVariable Long id);
}
