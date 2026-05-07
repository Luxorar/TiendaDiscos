package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface SedeRepository extends JpaRepository<Sede, Long>{
    ArrayList<Sede> listaSedes = new ArrayList<>();

    default Sede PutSede(Sede s){
        listaSedes.add(s);
        return s;
    }

    default ArrayList<Sede> getListaSedes(){
        return listaSedes;
    }

    default Sede getSedeId(Long id){
        for(Sede s: listaSedes){
            if(s.equals(id)){
                return s;
            }
        }
        return null;
    }

    default Sede postSedeNum(Long id, String num){
        for(Sede sede: listaSedes){
            if(sede.getId()==id){
                sede.setNumberSedeTelefono(num);
                return sede;
            }
        }
        return null;
    }

    default String deleteSede(Long id){
        for(Sede s:listaSedes){
            if(s.getId() == id){
                listaSedes.remove(s);
                return "Resena eliminada";
            }
        }
        return "Resena no encontrada";
    }
}
