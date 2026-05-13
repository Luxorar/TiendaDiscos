package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscoService implements IDiscoService {
    @Autowired
    private DiscoRepository repo;

    public Disco postDisco(Disco d){ return repo.save(d);}

    public Disco getDiscoId(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
    }

    public String putDisco(Long id, Disco d){
        Disco disc = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrado"));

        disc.setNombreDisco(d.getNombreDisco());
        disc.setArtista(d.getArtista());
        disc.setPrecio(d.getPrecio());
        return "Disco modificado";
    }

    public String deleteDisco(Long id){
        Disco disc = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        repo.delete(disc);
        return "Disco eliminado";
    }
}
