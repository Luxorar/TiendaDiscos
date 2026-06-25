package com.TiendaDisco.RegistrarSede.service;

import com.TiendaDisco.RegistrarSede.dto.ProductoDTO;
import com.TiendaDisco.RegistrarSede.exception.ManejoErrores;
import com.TiendaDisco.RegistrarSede.mapper.Mapper;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.repository.ProductoRepository;
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

    public ProductoDTO getProductoId(Long id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
        return Mapper.toDTO(producto);
    }

    public List<ProductoDTO> getAllProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    public String putProducto(Long id, Producto p) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));
        producto.setNombreProducto(p.getNombreProducto());
        producto.setPrecio(p.getPrecio());
        repo.save(producto);
        return "Datos del Producto modificados";
    }

    public String deleteProducto(Long id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(producto);
        return "Producto elimiando";
    }
}
