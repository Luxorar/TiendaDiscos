package com.TiendaDisco.CarritoCompras.mapper;

import com.TiendaDisco.CarritoCompras.client.DiscoClient;
import com.TiendaDisco.CarritoCompras.client.ProductoClient;
import com.TiendaDisco.CarritoCompras.client.UserClient;
import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.dto.CarritoDiscoDTO;
import com.TiendaDisco.CarritoCompras.dto.DiscoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.CarritoDisco;
import com.TiendaDisco.CarritoCompras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        for (CarritoDisco cd : c.getDiscosAgregados()) {
            try {
                DiscoDTO disco = discoClient.obtenerDiscoPorId(cd.getDiscoId()).getBody();
                if (disco != null) {
                    suma += disco.getPrecio() * cd.getQty();
                }
            } catch (Exception e) {
                // servicio caído, se omite el precio
            }
        }

        List<Producto> listaProductos = c.getProductosAgregados();
        for (Producto pro : listaProductos) {
            try {
                suma += productoClient.obtenerProductoPorId(pro.getId()).getBody().getPrecio();
            } catch (Exception e) {
                // servicio caído, se omite el precio
            }
        }

        List<CarritoDiscoDTO> discosDTO = new ArrayList<>();
        for (CarritoDisco cd : c.getDiscosAgregados()) {
            try {
                DiscoDTO disco = discoClient.obtenerDiscoPorId(cd.getDiscoId()).getBody();
                if (disco != null) {
                    discosDTO.add(CarritoDiscoDTO.builder()
                            .id(cd.getId())
                            .discoId(cd.getDiscoId())
                            .qty(cd.getQty())
                            .nombreDisco(disco.getNombreDisco())
                            .artista(disco.getArtista())
                            .precio(disco.getPrecio())
                            .imagen(disco.getImagen())
                            .build());
                }
            } catch (Exception e) {
                discosDTO.add(CarritoDiscoDTO.builder()
                        .id(cd.getId())
                        .discoId(cd.getDiscoId())
                        .qty(cd.getQty())
                        .nombreDisco("Disco #" + cd.getDiscoId())
                        .precio(0)
                        .build());
            }
        }

        return CarritoDTO.builder()
                .id(c.getId())
                .user(userClient.getUserId(c.getUserId()).getUserName())
                .precioSolid(suma)
                .productosAgregados(c.getProductosAgregados())
                .discosAgregados(discosDTO)
                .descuento(c.getDescuento())
                .precioLiquido((int) Math.round(suma * (100.0 - c.getDescuento()) / 100.0))
                .build();
    }
}
