package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoService implements IDiscoService{
    @Autowired
    private DiscoRepository repo;

    public Disco postDisco(Disco d){
        return repo.save(d);
    }

    public List<Disco> getAllDiscos(){
        return repo.findAll();
    }

    public Disco getDiscoId(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
    }

    public String putDisco(Long id, Disco d){
        Disco disc = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrada"));

        disc.setNombreDisco(d.getNombreDisco());
        disc.setArtista(d.getArtista());
        return "Usuario modificado";
    }

    public String deleteDisco(Long id){
        Disco disc = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));
        repo.delete(disc);
        return "Disco elimiando";
    }
}
