package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.model.Carrito;

import java.util.List;

/**
 * Servicio de logica de negocio para el carrito de compras.
 * Define las operaciones disponibles para gestionar carritos.
 */
public interface ICarritoService {

    /**
     * Obtiene todos los carritos registrados.
     *
     * @return lista de {@link CarritoDTO}
     */
    List<CarritoDTO> getListaCarrito();

    /**
     * Crea un nuevo carrito de compras.
     *
     * @param c objeto {@link Carrito} con los datos iniciales
     * @return el carrito creado
     */
    Carrito postCarrito(Carrito c);

    /**
     * Obtiene el carrito de un usuario por su identificador.
     *
     * @param usuario identificador del usuario
     * @return {@link CarritoDTO} del usuario
     */
    CarritoDTO getCarrito(Long usuario);

    /**
     * Actualiza el descuento del carrito de un usuario.
     *
     * @param c       objeto con el descuento actualizado
     * @param usuario identificador del usuario
     * @return mensaje de confirmacion
     */
    String updateCarrito(Carrito c, Long usuario);

    /**
     * Elimina el carrito completo de un usuario.
     *
     * @param usuario identificador del usuario
     */
    void deleteCarrito(Long usuario);
}
