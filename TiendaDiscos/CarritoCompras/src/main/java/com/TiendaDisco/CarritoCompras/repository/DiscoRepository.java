package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;

public interface DiscoRepository extends CarritoRepository{

    default Disco putDisco(String user, Disco disco){
        for(Carrito c :listaCarrito){
            if(c.getUser().equals(user)){
                c.getDiscosAgregados().add(disco);
                return disco;
            }
        }
        return null;
    }

    default Disco getDisco(String user, Long idDisco){
        for (Carrito c: listaCarrito){
            if(c.getUser().equals(user)){
                for(Disco disco: c.getDiscosAgregados()){
                    if(disco.getId().equals(idDisco)){
                        return disco;
                    }
                }
            }
        }
        return null;
    }

    default String deleteDiscos(String user, Long idDisco){
        for(Carrito c: listaCarrito){
            if(c.getUser().equals(user)){
                for(Disco disco: c.getDiscosAgregados()){
                    if(disco.getId().equals(idDisco)){
                        c.getDiscosAgregados().remove(disco);
                        return "Disco eliminado";
                    }
                }
            }
        }
        return "No se a encontrado el disco";
    }

    default Disco postDisco(String user, Long idDisco, Disco newDisco){
        for(Carrito c : listaCarrito){
            if(c.getUser().equals(user)){
                for(Disco disco : c.getDiscosAgregados()){
                    if(disco.getId().equals(idDisco)){
                        disco.setNombreDisco(newDisco.getNombreDisco());
                        disco.setPrecio(newDisco.getPrecio());
                        disco.setArtista(newDisco.getArtista());

                        return disco;
                    }
                }
            }
        }
        return null;
    }
}
