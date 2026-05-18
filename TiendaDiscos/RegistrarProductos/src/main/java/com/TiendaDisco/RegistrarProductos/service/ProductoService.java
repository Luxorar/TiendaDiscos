package com.TiendaDisco.RegistrarProductos.service;

import com.TiendaDisco.RegistrarProductos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarProductos.model.Producto;
import com.TiendaDisco.RegistrarProductos.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    public Producto postProducto(Producto p) {
        return repo.save(p);
    }

    public List<Producto> getListaProducto() {
        return repo.findAll().stream().toList();
    }

    public Producto getProductoID(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado"));
    }

    public List<Producto> getProductoNombre(String nombre) {
        return repo.findByNombreProducto(nombre);
    }

    public String deleteProducto(Long id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Producto a eliminar no encontrado"));
        repo.delete(producto);
        return "Producto eliminado";
    }

    public List<Producto> getProductoMarca(String marca) {
        return repo.findByMarca(marca);
    }
}