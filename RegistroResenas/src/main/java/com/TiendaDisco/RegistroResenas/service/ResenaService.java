package com.TiendaDisco.RegistroResenas.service;


import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService implements IResenaService {
    @Autowired
    private ResenaRepository repo;

    public Resena postResena(Resena r){
        return repo.save(r);
    }

    public List<Resena> getAllResenas(){
        return repo.findAll();
    }

    public Resena getResenaId(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
    }

    public String deleteResena(Long id){
        Resena resena = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Reseña no encontrada"));
        repo.delete(resena);
        return "Reseña eliminada";
    }
}
