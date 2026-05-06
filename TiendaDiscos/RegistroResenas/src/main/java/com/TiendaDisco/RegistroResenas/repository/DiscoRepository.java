package com.TiendaDisco.RegistroResenas.repository;

import com.TiendaDisco.RegistroResenas.model.Disco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface DiscoRepository extends JpaRepository<Disco, Long> {

    ArrayList<Disco> listaDiscos = new ArrayList<>();

    default Disco postDisco(Disco d){
        listaDiscos.add(d);
        return d;
    }

    default Disco getDiscoId(Long id){
        for(Disco d: listaDiscos) {
            if (id == d.getId()) {
                return d;
            }
        }
        return null;
    }

    default String putDisco(Long id, Disco d){
        for(Disco dis : listaDiscos){
            if(dis.getId()==id){
                dis.setNombreDisco(d.getNombreDisco());
                dis.setArtista(d.getArtista());

                return "Actualizacion exitosa";
            }
        }
        return "Los cmabios no se han realizado";
    }

    default String deleteDisco(Long id){
        for(Disco d:listaDiscos){
            if(d.getId() == id){
                listaDiscos.remove(d);
                return "Disco eliminado";
            }
        }
        return "Disco no encontrado";
    }
}
