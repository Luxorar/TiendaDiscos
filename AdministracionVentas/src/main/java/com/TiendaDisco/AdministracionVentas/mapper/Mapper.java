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

/**
 * Clase utilitaria para mapear entidades del dominio a DTOs.
 * <p>Convierte una entidad {@link Venta} a {@link VentaDTO}, consultando
 * los microservicios de productos, discos y usuarios para obtener
 * informacion enriquecida.</p>
 *

 * @author Luis Villalon
 * @version 1.0.0
 */
@Component
public class Mapper {

    @Autowired
    private DiscoClient discoClient;

    @Autowired
    private ProductoClient productoClient;

    @Autowired
    private UserClient userClient;

    /**
     * Convierte una entidad {@link Venta} a su DTO correspondiente,
     * enriqueciendo los nombres de productos y discos mediante Feign clients.
     *
     * @param c entidad Venta a convertir, puede ser {@code null}
     * @return {@link VentaDTO} con los datos mapeados, o {@code null} si la entrada es {@code null}
     */
    public VentaDTO toDTO(Venta c) {
        if (c == null) return null;

        List<Producto> listaP = c.getProductosComprados();
        List<String> listaProductosString = new ArrayList<>();
        int subTotalCalculado = 0;

        for (Producto p : listaP) {

            String nombre;

            if (p.getTipo() == TipoProducto.DISCO && p.getIdProducto() != null) {
                try {
                    ResponseEntity<DiscoDTO> response = discoClient.obtenerDiscoPorId(p.getIdProducto());
                    if (response.getBody() != null) {
                        nombre = response.getBody().getNombreDisco();
                        subTotalCalculado += response.getBody().getPrecio();
                        listaProductosString.add(nombre);
                    }
                } catch (Exception e) {
                }
            } else if (p.getTipo() == TipoProducto.PRODUCTO && p.getIdProducto() != null) {
                try {
                    ResponseEntity<ProductoDTO> response = productoClient.obtenerProductoPorId(p.getIdProducto());
                    if (response.getBody() != null) {
                        nombre = response.getBody().getNombreProducto();
                        subTotalCalculado += response.getBody().getPrecio();
                        listaProductosString.add(nombre);
                    }
                } catch (Exception e) {
                }
            }

        }

        return VentaDTO.builder()
                .id(c.getId())
                .productosComprados(listaProductosString)
                .fechaVenta(c.getFechaVenta())
                .usuario(userClient.getUserId(c.getUsuario()).getUserName())
                .puntosUsados(c.getPuntosUsados())
                .puntosGanados(c.getPuntosGanados())
                .subtotal(subTotalCalculado)
                .descuento(c.getDescuento())
                .totalPagar((int) Math.round(subTotalCalculado * (100.0 - c.getDescuento()) / 100.0))
                .build();
        }
    }


