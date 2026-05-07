package com.TiendaDisco.RegistrarProductos.Repository;

import com.TiendaDisco.RegistrarProductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    ArrayList<Producto> ListaProductos = new ArrayList<>();

    //REGISTRAR PRODUCTO
    default Producto postProducto(Producto producto) {
        ListaProductos.add(producto);
        return producto;
    }

    //OBTENER TODOS LOS PRODUCTOS
    default List<Producto> ObtenerTodos() {
        return ListaProductos;
    }

    //OBTENER POR ID
    default Producto obtenerProductoPorId(Long id) {
        for (Producto p : ListaProductos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    //OBTENER POR NOMBRE
    default Producto obtenerProductoPorNombre(String nombreProducto) {
        for (Producto p : ListaProductos) {
            if (p.getNombreProducto().equalsIgnoreCase(nombreProducto)) {
            }
        }
        return null;
    }

    //ELIMINAR POR ID
    default boolean eliminar(Long id) {
        for (int i = 0; i < ListaProductos.size(); i++) {
            if (ListaProductos.get(i).getId().equals(id)) {
                ListaProductos.remove(i);
                return true;
            }
        }
        return false;
    }

    //OBTENER TODOS LOS PRODUCTOS DE UNA MARCA
     default List<Producto> obtenerProductosPorMarca(String marca) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto p : ListaProductos) {
            if (p.getMarcaProducto().equalsIgnoreCase(marca));{
                productosFiltrados.add(p);
            }
        }
        return productosFiltrados;
     }
}