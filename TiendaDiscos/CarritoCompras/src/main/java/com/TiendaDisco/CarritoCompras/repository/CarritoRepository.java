package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CarritoRepository extends JpaRepository<Carrito,Long> {
    ArrayList<Carrito> listaCarrito=new ArrayList<>();

    default ArrayList<Carrito> getListaCarrito(){return listaCarrito;}

    default Carrito postCarrito(Carrito c){
        listaCarrito.add(c);
        return c;
    }

    default Carrito getCarrito(String usuario){
        for (Carrito c : listaCarrito){
            if(c.getUser().equals(usuario)){
                return c;
            }
        }
        return null;
    }

    default String deleteCarrito(String usuario){
        for (Carrito c : listaCarrito){
            if(c.getUser().equals(usuario)){
                listaCarrito.remove(c);
                return "Carrito "+usuario+" eliminado";
            }
        }
        return "Carrito "+usuario+" no encontrado";
    }

    default String updateCarrito(Carrito c, String usuario){
        for (Carrito c2 : listaCarrito){
            if(c2.getUser().equals(usuario)){
                c2.setProductosAgregados(c.getProductosAgregados());
                c2.setDiscosAgregados(c.getDiscosAgregados());
                c2.setSumaPrecios(c.getSumaPrecios());

                return "Carrito "+usuario+" actualizado";
            }
        }
        return "Carrito "+usuario+" no encontrado";
    }

}
