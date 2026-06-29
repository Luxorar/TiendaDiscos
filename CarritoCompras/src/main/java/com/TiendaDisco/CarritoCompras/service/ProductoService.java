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

@Service
@Transactional
public class ProductoService implements IProductoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    //==================OBTIENE TODOS LOS PRODUCTOS================================
    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    //==================AGREGA PRODUCTO AL CARRITO================================
    @Override
    public Producto postProducto(Long user, Long idProducto, Producto newProducto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Producto saved = productoRepository.save(newProducto);
        carro.getProductosAgregados().add(saved);
        carritoRepository.save(carro);
        return saved;
    }

    //==================OBTIENE PRODUCTOS DEL CARRITO================================
    @Override
    public ArrayList<Producto> getListaProducto(Long user, Producto producto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return new ArrayList<>(carro.getProductosAgregados());
    }

    //==================OBTIENE PRODUCTO DEL CARRITO================================
    @Override
    public Producto getProducto(Long user, Long idProducto) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return carro.getProductosAgregados().stream()
                .filter(p -> p.getId().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado en el carrito"));
    }

    //==================ELIMINA PRODUCTO DEL CARRITO================================
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

    //==================MODIFICA PRODUCTO EN EL CARRITO================================
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
