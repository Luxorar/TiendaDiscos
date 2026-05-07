package com.TiendaDisco.RegistrarDiscos.Repository;

import com.TiendaDisco.RegistrarDiscos.model.Disco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface DiscoRepository extends JpaRepository<Disco,Long>{
    ArrayList<Disco> ListaDiscos = new ArrayList<>();

    //REGISTRAR DISCO
    default Disco postDisco(Disco disco){
        ListaDiscos.add(disco);
        return disco;
    }

    //OBTENER TODOS LOS DISCOS
    default List<Disco> ObtenerTodos(){
        return ListaDiscos;
    }

    //OBTENER DISCOS POR ID
    default Disco ObtenerDiscoPorId(Long id){
        for (Disco d : ListaDiscos){
            if (d.getId().equals(id)){
                return d;
            }
        }
        return null;
    }

    //OBTENER DISCO POR NOMBRE
    default Disco ObtenerPorNombreDisco(String nombreDisco){
        for (Disco d : ListaDiscos){
            if (d.getNombreDisco().equalsIgnoreCase(nombreDisco)){
            }
        }
        return null;
    }

    //ELIMINAR DISCO POR ID
    default boolean eliminar(Long id){
        for (int i = 0; i < ListaDiscos.size(); i++){
            if (ListaDiscos.get(i).getId().equals(id)){
                ListaDiscos.remove(i);
                return true;
            }
        }
        return false;
    }
}