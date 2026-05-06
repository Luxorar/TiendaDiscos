package com.TiendaDisco.RegistroResenas.repository;
import com.TiendaDisco.RegistroResenas.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    ArrayList<Resena> listaResenas = new ArrayList<>();

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
