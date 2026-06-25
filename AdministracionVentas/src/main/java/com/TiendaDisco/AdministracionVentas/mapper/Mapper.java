package com.TiendaDisco.AdministracionVentas.mapper;

import com.TiendaDisco.AdministracionVentas.client.DiscoClient;
import com.TiendaDisco.AdministracionVentas.client.ProductoClient;
import com.TiendaDisco.AdministracionVentas.client.UserClient;
import com.TiendaDisco.AdministracionVentas.dto.DiscoDTO;
import com.TiendaDisco.AdministracionVentas.dto.ProductoDTO;
import com.TiendaDisco.AdministracionVentas.dto.UserDTO;
import com.TiendaDisco.AdministracionVentas.dto.VentaDTO;
import com.TiendaDisco.AdministracionVentas.model.Producto;
import com.TiendaDisco.AdministracionVentas.model.TipoProducto;
import com.TiendaDisco.AdministracionVentas.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public VentaDTO toDTO(Venta c) {
        if (c == null) return null;

        List<Producto> listaP = c.getProductosComprados();
        List<String> listaProductosString = new ArrayList<>();
        int subTotalCalculado = 0;

        for (Producto p : listaP) {
            subTotalCalculado += p.getPrecio();
            String nombre = p.getNombre();

            if (p.getTipo() == TipoProducto.DISCO && p.getIdProducto() != null) {
                try {
                    ResponseEntity<DiscoDTO> response = discoClient.obtenerDiscoPorId(p.getIdProducto());
                    if (response.getBody() != null) {
                        nombre = response.getBody().getNombreDisco();
                    }
                } catch (Exception e) {
                }
            } else if (p.getTipo() == TipoProducto.PRODUCTO && p.getIdProducto() != null) {
                try {
                    ResponseEntity<ProductoDTO> response = productoClient.obtenerProductoPorId(p.getIdProducto());
                    if (response.getBody() != null) {
                        nombre = response.getBody().getNombreProducto();
                    }
                } catch (Exception e) {
                }
            }

            listaProductosString.add(nombre);
        }

        return VentaDTO.builder()
                .id(c.getId())
                .productosComprados(listaProductosString)
                .fechaVenta(c.getFechaVenta())
                .usuario(getUserEmail(c.getUsuario()))
                .puntosUsados(c.getPuntosUsados())
                .puntosGanados(c.getPuntosGanados())
                .subtotal(subTotalCalculado)
                .descuento(c.getDescuento())
                .totalPagar((int) Math.round(subTotalCalculado * (100.0 - c.getDescuento()) / 100.0))
                .build();
    }

    private String getUserEmail(Long usuarioId) {
        if (usuarioId == null) return "Sin usuario";
        try {
            UserDTO user = userClient.getUserId(usuarioId);
            return user != null ? user.getUserName() : "Sin usuario";
        } catch (Exception e) {
            return "Sin usuario";
        }
    }
}
