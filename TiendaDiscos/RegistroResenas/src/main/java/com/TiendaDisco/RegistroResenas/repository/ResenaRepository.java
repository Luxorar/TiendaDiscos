package com.TiendaDisco.RegistroResenas.repository;
import com.TiendaDisco.RegistroResenas.model.Resena;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResenaRepository {
    ArrayList<Resena> listaResenas = new ArrayList<>();

    Long contador = 1L;

    default Resena postResena(Resena r){
        listaResenas.add(r);
        return r;
    }

    default Resena getResenaId(Long id){
        for(Resena r: listaResenas) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }

    default String putResena(Long id, Resena r){
        for(Resena res : listaResenas){
            if(res.getId()==id){
                res.setMensaje(r.getMensaje());

                return "Actualizacion exitosa";
            }
        }
        return "Los cmabios no se han realizado";
    }

    default String deleteResena(Long id){
        for(Resena r:listaResenas){
            if(r.getId() == id){
                listaResenas.remove(r);
                return "Resena eliminada";
            }
        }
        return "Resena no encontrada";
    }

}
