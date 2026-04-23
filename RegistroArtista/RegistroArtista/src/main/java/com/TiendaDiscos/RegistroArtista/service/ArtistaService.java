package com.TiendaDiscos.RegistroArtista.service;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.TiendaDiscos.RegistroArtista.model.Artista;
import com.TiendaDiscos.RegistroArtista.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public ArrayList<Artista> getAllArtistas() {
        return artistaRepository.getAllArtistas();
    }

    public Artista registrarArtista(Artista art) {
        return artistaRepository.registrarArtista(art);
    }

    public Artista getArtistaId(Long id) {
        return artistaRepository.getArtistaId(id);
    }

    public Artista getArtistaNombre(String n) {
        return artistaRepository.getArtistaNombre(n);
    }

    public Artista actualizarArtista(Long id, Artista art) {
        return artistaRepository.actualizarArtista(id, art);
    }

    public String deleteArtista(Long id) {
        return artistaRepository.deleteArtista(id);
    }
}
