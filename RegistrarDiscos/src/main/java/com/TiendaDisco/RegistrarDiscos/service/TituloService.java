package com.TiendaDisco.RegistrarDiscos.service;

import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.repository.TituloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TituloService implements ITituloService {

    @Autowired
    private TituloRepository repo;

    @Override
    public Titulo postTitulo(Titulo t) {
        return repo.save(t);
    }

    @Override
    public Titulo getTituloId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
    }

    @Override
    public String putTitulo(Long id, Titulo t) {
        Titulo tit = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrado"));

        tit.setTitulo(t.getTitulo());

        repo.save(tit);
        return "Titulo modificado";
    }

    @Override
    public String deleteTitulo(Long id) {
        Titulo disc = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        repo.delete(disc);
        return "Titulo eliminado";
    }
}