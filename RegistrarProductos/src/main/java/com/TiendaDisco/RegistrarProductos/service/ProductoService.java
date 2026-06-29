package com.TiendaDisco.RegistrarProductos.service;

import com.TiendaDisco.RegistrarProductos.dto.ProductoDTO;
import com.TiendaDisco.RegistrarProductos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarProductos.mapper.Mapper;
import com.TiendaDisco.RegistrarProductos.model.Producto;
import com.TiendaDisco.RegistrarProductos.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    //==================REGISTRA UN PRODUCTO================================
    public Producto postProducto(Producto p) {
        return repo.save(p);
    }

    //==================OBTIENE TODOS LOS PRODUCTOS================================
    public List<ProductoDTO> getAllProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    //==================OBTIENE PRODUCTO POR ID================================
    public ProductoDTO getProductoID(Long id) {
        return repo.findById(id)
                .map(Mapper::toDTO)
                .orElseThrow(() -> new ManejoErrores("Producto no encontrado"));
    }

    //==================OBTIENE PRODUCTO POR NOMBRE================================
    public List<ProductoDTO> getProductoNombre(String nombre) {
        return Mapper.toDTOList(repo.findByNombreProducto(nombre));
    }

    //==================ELIMINA UN PRODUCTO================================
    public String deleteProducto(Long id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Producto a eliminar no encontrado"));
        repo.delete(producto);
        return "Producto eliminado";
    }

    //==================OBTIENE PRODUCTO POR MARCA================================
    public List<ProductoDTO> getProductoMarca(String marca) {
        return Mapper.toDTOList(repo.findByMarca(marca));
    }
}
