package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;

import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private ProductoRepository repo;

    public Producto postProducto(Producto p){
        return repo.save(p);
    }

    public Producto getProductoId(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
    }

    public String putProducto(Long id, Producto p){
        Producto prod = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        prod.setNombreProducto(p.getNombreProducto());
        prod.setPrecio(p.getPrecio());
        return "Datos del Producto modificados";
    }

    public String deleteProducto(Long id){
        Producto p = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(p);
        return "Producto elimiando";
    }
}
