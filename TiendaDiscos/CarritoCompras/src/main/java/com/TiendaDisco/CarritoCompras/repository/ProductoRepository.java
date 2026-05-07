package com.TiendaDisco.CarritoCompras.repository;


import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;

import java.util.ArrayList;

public interface ProductoRepository extends CarritoRepository{

    default Producto putProducto(String user, Producto producto){
        for(Carrito c : listaCarrito){
            if(c.getUser().equals(user)){
                c.getProductosAgregados().add(producto);
                return producto;
            }
        }
        return null;
    }

    default ArrayList<Producto> getListaProducto(String user, Producto producto){
        for (Carrito c: listaCarrito){
            if(c.getUser().equals(user)){
                return c.getProductosAgregados();
            }
        }
        return null;
    }

    default Producto getProducto(String user, Long idProducto){
        for (Carrito c: listaCarrito){
            if(c.getUser().equals(user)){
                for(Producto producto: c.getProductosAgregados()){
                    if(producto.getId().equals(idProducto)){
                        return producto;
                    }
                }
            }
        }
        return null;
    }

    default String deleteProducto(String user, Long idProducto){
        for(Carrito c: listaCarrito){
            if(c.getUser().equals(user)){
                for(Producto producto: c.getProductosAgregados()){
                    if(producto.getId().equals(idProducto)){
                        c.getProductosAgregados().remove(producto);
                        return "Producto eliminado";
                    }
                }
            }
        }
        return "No se a encontrado el producto";
    }

    default Producto postProducto(String user, Long idProducto, Producto newProducto){
        for(Carrito c : listaCarrito){
            if(c.getUser().equals(user)){
                for(Producto producto : c.getProductosAgregados()){
                    if(producto.getId().equals(idProducto)){
                        producto.setNombreProducto(newProducto.getNombreProducto());
                        producto.setPrecio(newProducto.getPrecio());

                        return producto;
                    }
                }
            }
        }
        return null;
    }
}
