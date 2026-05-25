package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto postProducto(String user, Long idProducto, Producto newProducto) {
        Carrito carro = carritoRepository.findByUserUserName(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Producto saved = productoRepository.save(newProducto);
        carro.getProductosAgregados().add(saved);
        carritoRepository.save(carro);
        return saved;
    }

    @Override
    public ArrayList<Producto> getListaProducto(String user, Producto producto) {
        Carrito carro = carritoRepository.findByUserUserName(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return new ArrayList<>(carro.getProductosAgregados());
    }

    @Override
    public Producto getProducto(String user, Long idProducto) {
        Carrito carro = carritoRepository.findByUserUserName(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return carro.getProductosAgregados().stream()
                .filter(p -> p.getId().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado en el carrito"));
    }

    @Override
    public String deleteProducto(String user, Long idProducto) {
        Carrito carro = carritoRepository.findByUserUserName(user)
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

    @Override
    public Producto putProducto(String user, Producto producto) {
        Carrito carro = carritoRepository.findByUserUserName(user)
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
