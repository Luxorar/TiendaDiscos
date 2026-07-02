package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;


import java.util.List;

/**
 * Interface que define las operaciones de negocio para el carrito de compras.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
public interface ICarritoService {

    /**
     * Obtiene todos los carritos registrados.
     *
     * @return lista de {@link CarritoDTO}
     */
    List<CarritoDTO> getListaCarrito();

    /**
     * Crea un nuevo carrito.
     *
     * @param c entidad {@link Carrito} a persistir
     * @return {@link Carrito} creado
     */
    Carrito postCarrito(Carrito c);

    /**
     * Obtiene el carrito de un usuario.
     *
     * @param usuario identificador del usuario
     * @return {@link CarritoDTO} del usuario
     */
    CarritoDTO getCarrito(Long usuario);

    /**
     * Actualiza el descuento del carrito de un usuario.
     *
     * @param c       carrito con el nuevo descuento
     * @param usuario identificador del usuario
     * @return mensaje de confirmacion
     */
    String updateCarrito(Carrito c, Long usuario);

    /**
     * Elimina el carrito de un usuario.
     *
     * @param usuario identificador del usuario
     */
    void deleteCarrito(Long usuario);
}
