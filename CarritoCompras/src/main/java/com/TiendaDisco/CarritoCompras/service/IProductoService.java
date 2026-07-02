package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de logica de negocio para productos del carrito.
 * Define las operaciones disponibles para gestionar productos en el carrito.
 */
public interface IProductoService {

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos
     */
    List<Producto> getAllProductos();

    /**
     * Modifica un producto existente en el carrito de un usuario.
     *
     * @param user    identificador del usuario
     * @param producto objeto con los datos actualizados
     * @return el producto modificado
     */
    Producto putProducto(Long user, Producto producto);

    /**
     * Obtiene la lista de productos del carrito de un usuario.
     *
     * @param user     identificador del usuario
     * @param producto filtro opcional
     * @return lista de productos del carrito
     */
    ArrayList<Producto> getListaProducto(Long user, Producto producto);

    /**
     * Obtiene un producto especifico del carrito de un usuario.
     *
     * @param user       identificador del usuario
     * @param idProducto identificador del producto
     * @return el producto solicitado
     */
    Producto getProducto(Long user, Long idProducto);

    /**
     * Elimina un producto del carrito de un usuario.
     *
     * @param user       identificador del usuario
     * @param idProducto identificador del producto
     * @return mensaje de confirmacion
     */
    String deleteProducto(Long user, Long idProducto);

    /**
     * Agrega un nuevo producto al carrito de un usuario.
     *
     * @param user         identificador del usuario
     * @param idProducto   identificador del producto
     * @param newProducto  datos del producto a agregar
     * @return el producto agregado
     */
    Producto postProducto(Long user, Long idProducto, Producto newProducto);
}
