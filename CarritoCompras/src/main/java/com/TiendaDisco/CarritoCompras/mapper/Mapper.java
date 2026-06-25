package com.TiendaDisco.CarritoCompras.mapper;

import com.TiendaDisco.CarritoCompras.client.DiscoClient;
import com.TiendaDisco.CarritoCompras.client.ProductoClient;
import com.TiendaDisco.CarritoCompras.client.UserClient;
import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    @Autowired
    private DiscoClient discoClient;

    @Autowired
    private ProductoClient productoClient;

    @Autowired
    private UserClient userClient;

    public CarritoDTO toDTO(Carrito c) {
        if (c == null) return null;
        int suma = 0;

        List<Disco> listaDiscos = c.getDiscosAgregados();
        List<Producto> listaProductos = c.getProductosAgregados();

        for (Disco disc : listaDiscos) {
            try {
                suma += discoClient.obtenerDiscoPorId(disc.getId()).getBody().getPrecio();
            } catch (Exception e) {
                // servicio caído, se omite el precio
            }
        }

        for (Producto pro : listaProductos) {
            try {
                suma += productoClient.obtenerProductoPorId(pro.getId()).getBody().getPrecio();
            } catch (Exception e) {
                // servicio caído, se omite el precio
            }
        }

        return CarritoDTO.builder()
                .id(c.getId())
                .user(userClient.getUserId(c.getUserId()).getUserName())
                .precioSolid(suma)
                .productosAgregados(c.getProductosAgregados())
                .discosAgregados(c.getDiscosAgregados())
                .descuento(c.getDescuento())
                .precioLiquido((int) Math.round(suma * (100.0 - c.getDescuento()) / 100.0))
                .build();
    }
}
