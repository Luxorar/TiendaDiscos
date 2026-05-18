package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscoService implements IDiscoService {
    @Autowired
    private DiscoRepository discoRepository;

    public Disco postDisco(Disco d){ return discoRepository.save(d);}

    public Disco getDiscoId(Long id){
        return discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
    }

    public String putDisco(Long id, Disco d){
        Disco disc = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrado"));

        disc.setNombreDisco(d.getNombreDisco());
        disc.setArtista(d.getArtista());
        disc.setPrecio(d.getPrecio());
        return "Disco modificado";
    }

    public String deleteDisco(Long id){
        Disco disc = discoRepository.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        discoRepository.delete(disc);
        return "Disco eliminado";
    }
}
