package com.TiendaDisco.RegistrarDiscos.service;

import java.util.List;
import com.TiendaDisco.RegistrarDiscos.exception.ManejoErrores;
import com.TiendaDisco.RegistrarDiscos.model.Titulo;
import com.TiendaDisco.RegistrarDiscos.repository.TituloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la logica de negocio para las entidades de titulo.
 * Permite administrar las canciones o pistas asociadas a los discos del catalogo.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class TituloService implements ITituloService {

    @Autowired
    private TituloRepository repo;

    /**
     * Registra un nuevo titulo en la base de datos.
     * @param t El objeto {@link Titulo} con la informacion de la cancion a registrar.
     * @return El titulo guardado junto con el ID autogenerado
     */
    @Override
    public Titulo postTitulo(Titulo t) {
        return repo.save(t);
    }

    /**
     * Busca y obtiene la informacion de un tiutlo en base a su id.
     * @param id El identificador del titulo.
     * @return Un objeto {@link Titulo} con los detalles de la cancion.
     * @throws ManejoErrores Si no existe ninguna cancion con el id indicado.
     */
    @Override
    public Titulo getTituloId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrado"));
    }

    /**
     * Busca y actualiza un titulo en base a su ID.
     * @param id El identificador del titulo.
     * @param t Cambio a realzar.
     * @return Un mensaje tipo String indicando la modificacion.
     * @throws ManejoErrores Si no existe ninguna cancion con el id indicado.
     */
    @Override
    public String putTitulo(Long id, Titulo t) {
        Titulo tit = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a modificar no encontrado"));

        tit.setTitulo(t.getTitulo());

        repo.save(tit);
        return "Titulo modificado";
    }

    /**
     * Busca y elimina un titulo en base a su id.
     * @param id El id del titulo
     * @return Un mensaje tipo String indicando la eliminacion.
     * @throws ManejoErrores Si no existe ninguna cancion con el id indicado.
     */
    @Override
    public String deleteTitulo(Long id) {
        Titulo disc = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id a eliminar no encontrado"));

        repo.delete(disc);
        return "Titulo eliminado";
    }

    /**
     * Obtiene todos los titulos guardados
     * @return Todos los objetos guardados
     */
    @Override
    public List<Titulo> getAllTitulos() {
        return repo.findAll();
    }
}
