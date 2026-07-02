package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion del servicio de productos del carrito.
 * Contiene la logica de negocio para gestionar productos dentro del carrito.
 */
@Service
@Transactional
public class ProductoService implements IProductoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos
     */
    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    /**
     * Agrega un nuevo producto al carrito de un usuario.
     *
     * @param user         identificador del usuario
     * @param idProducto   identificador del producto
     * @param newProducto  datos del producto a agregar
     * @return el producto persistido
     */
    @Override
    public Producto postProducto(Long user, Long idProducto, Producto newProducto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Producto saved = productoRepository.save(newProducto);
        carro.getProductosAgregados().add(saved);
        carritoRepository.save(carro);
        return saved;
    }

    /**
     * Obtiene la lista de productos del carrito de un usuario.
     *
     * @param user     identificador del usuario
     * @param producto filtro opcional
     * @return lista de productos en el carrito
     */
    @Override
    public ArrayList<Producto> getListaProducto(Long user, Producto producto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return new ArrayList<>(carro.getProductosAgregados());
    }

    /**
     * Obtiene un producto especifico del carrito de un usuario.
     *
     * @param user       identificador del usuario
     * @param idProducto identificador del producto
     * @return el producto solicitado
     */
    @Override
    public Producto getProducto(Long user, Long idProducto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return carro.getProductosAgregados().stream()
                .filter(p -> p.getId().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado en el carrito"));
    }

    /**
     * Elimina un producto del carrito de un usuario.
     *
     * @param user       identificador del usuario
     * @param idProducto identificador del producto
     * @return mensaje de confirmacion
     */
    @Override
    public String deleteProducto(Long user, Long idProducto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Producto prod = carro.getProductosAgregados().stream()
                .filter(p -> p.getId().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado en el carrito"));

        carro.getProductosAgregados().remove(prod);
        carritoRepository.save(carro);
        productoRepository.delete(prod);
        return "Producto eliminado del carrito";
    }

    /**
     * Modifica un producto existente en el carrito de un usuario.
     *
     * @param user     identificador del usuario
     * @param producto objeto con los datos actualizados
     * @return el producto modificado
     */
    @Override
    public Producto putProducto(Long user, Producto producto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Producto existente = carro.getProductosAgregados().stream()
                .filter(p -> p.getId().equals(producto.getId()))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado en el carrito"));

        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecio(producto.getPrecio());

        productoRepository.save(existente);
        carritoRepository.save(carro);
        return existente;
    }
}
