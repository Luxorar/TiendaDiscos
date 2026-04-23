package com.TiendaDiscos.Direcciones.repository;

import com.TiendaDiscos.Direcciones.model.Direccion;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface DireccionRepository {
    ArrayList<Direccion> listaDirecciones = new ArrayList<>();

    default String postDireccion(Direccion direc){
        direc.setId(Long.valueOf(listaDirecciones.size()));
        direc.setDireccion(direc.getDireccion().toLowerCase());
        direc.setCiudad(direc.getCiudad().toLowerCase());
        direc.setPais(direc.getPais().toLowerCase());
        listaDirecciones.add(direc);
        return "Dirección agregada";
    }

    default ArrayList<Direccion> getCiudadPais(String ciudad, String pais){
        ciudad.toLowerCase();
        pais.toLowerCase();
        ArrayList<Direccion> ciudadPais = new ArrayList<>();
        for(Direccion i: listaDirecciones){
            if(i.getPais().equals(pais)){
                if(i.getCiudad().equals(ciudad)){
                    ciudadPais.add(i);
                }
            }
        }
        return ciudadPais;
    }

    default Direccion getDireccionId(Long id){
        for(Direccion i: listaDirecciones){
            if(i.getId()==id){
                return i;
            }
        }
        return null;
    }

    default Direccion putDireccion(Long id, Direccion direc){
        for(Direccion i: listaDirecciones){
            if(i.getId()==id){
                i.setPais(direc.getPais());
                i.setCiudad(direc.getCiudad());
                i.setDireccion(direc.getDireccion());
                return direc;
            }
        }
        return null;
    }

    default String DeleteDireccion(Long id){
        for(Direccion i: listaDirecciones){
            if(i.getId()==id){
                listaDirecciones.remove(i);
                return "Dirección eliminada";
            }
        }
        return "id no encontrado";
    }
}
