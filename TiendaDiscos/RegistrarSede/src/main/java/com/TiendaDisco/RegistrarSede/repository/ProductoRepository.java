package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Producto;
import com.TiendaDisco.RegistrarSede.model.Sede;

import java.util.ArrayList;

public interface ProductoRepository extends SedeRepository{

    default Producto putProducto(Long idSede, Producto producto){
        for(Sede sede :listaSedes){
            if(sede.getId().equals(idSede)){
                sede.getProductosDisponible().add(producto);
                return producto;
            }
        }
        return null;
    }

    default ArrayList<Producto> getListaProducto(Long idSede, Producto producto){
        for (Sede sede: listaSedes){
            if(sede.getId().equals(idSede)){
                return sede.getProductosDisponible();
            }
        }
        return null;
    }

    default Producto getProducto(Long idSede, Long idProducto){
        for (Sede sede: listaSedes){
            if(sede.getId().equals(idSede)){
                for(Producto producto: sede.getProductosDisponible()){
                    if(producto.getId().equals(idProducto)){
                        return producto;
                    }
                }
            }
        }
        return null;
    }

    default String deleteProducto(Long idSede, Long idProducto){
        for(Sede sede: listaSedes){
            if(sede.getId().equals(idSede)){
                for(Producto producto: sede.getProductosDisponible()){
                    if(producto.getId().equals(idProducto)){
                        sede.getProductosDisponible().remove(producto);
                        return "Producto eliminado";
                    }
                }
            }
        }
        return "No se a encontrado el producto";
    }

    default Producto postProducto(Long idSede, Long idProducto, Producto newProducto){
        for(Sede sede : listaSedes){
            if(sede.getId().equals(idSede)){
                for(Producto producto : sede.getProductosDisponible()){
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
