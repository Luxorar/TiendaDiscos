package com.TiendaDiscos.RegistroArtista.repository;
import com.TiendaDiscos.RegistroArtista.model.Artista;

import java.util.ArrayList;

public interface ArtistaRepository {
    ArrayList<Artista> listaArtistas = new ArrayList<>();

    Long contador = 1L;

    default ArrayList<Artista> getAllArtistas(){return listaArtistas;}

    default Artista registrarArtista(Artista art){
        art.setId(contador);
        listaArtistas.add(art);
        return art;
    }

    default Artista getArtistaId(Long id){
        for(Artista a: listaArtistas){
            if(a.equals(id)){
                return a;
            }
        }
        return null;
    }

    default Artista getArtistaNombre(String n){
        for(Artista a: listaArtistas){
            if(a.equals(n)){
                return a;
            }
        }
        return null;
    }

    default Artista actualizarArtista(Long id, Artista art){
        for(Artista a: listaArtistas){
            if(a.getId() == art.getId()){
                Artista artista1 = new Artista();

                artista1.setNombre(art.getNombre());

                return artista1;
            }
        }
        return null;
    }

    default String deleteArtista(Long id){
        for (Artista a: listaArtistas){
            if(a.equals(id)) {
                listaArtistas.remove(a);
                return "Artista eliminado";
            }
        }
        return "Artista no encontrado";
    }
}
