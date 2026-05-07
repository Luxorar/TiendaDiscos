package com.TiendaDisco.RegistrarSede.repository;

import com.TiendaDisco.RegistrarSede.model.Disco;
import com.TiendaDisco.RegistrarSede.model.Sede;

import java.util.ArrayList;

public interface DiscoRepository extends SedeRepository{

    default Disco putDisco(Long idSede, Disco disco){
        for(Sede sede :listaSedes){
            if(sede.getId().equals(idSede)){
                sede.getDiscosDisponible().add(disco);
                return disco;
            }
        }
        return null;
    }

    default ArrayList<Disco> getListaDiscos(Long idSede, Disco disco){
        for (Sede sede: listaSedes){
            if(sede.getId().equals(idSede)){
                return sede.getDiscosDisponible();
            }
        }
        return null;
    }

    default Disco getDisco(Long idSede, Long idDisco){
        for (Sede sede: listaSedes){
            if(sede.getId().equals(idSede)){
                for(Disco disco: sede.getDiscosDisponible()){
                    if(disco.getId().equals(idDisco)){
                        return disco;
                    }
                }
            }
        }
        return null;
    }

    default String deleteDiscos(Long idSede, Long idDisco){
        for(Sede sede: listaSedes){
            if(sede.getId().equals(idSede)){
                for(Disco disco: sede.getDiscosDisponible()){
                    if(disco.getId().equals(idDisco)){
                        sede.getDiscosDisponible().remove(disco);
                        return "Disco eliminado";
                    }
                }
            }
        }
        return "No se a encontrado el disco";
    }

    default Disco postDisco(Long idSede, Long idDisco, Disco newDisco){
        for(Sede sede : listaSedes){
            if(sede.getId().equals(idSede)){
                for(Disco disco : sede.getDiscosDisponible()){
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
