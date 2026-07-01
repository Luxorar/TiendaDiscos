package com.TiendaDisco.RegistroResenas.service;

import com.TiendaDisco.RegistroResenas.DTO.ResenaDTO;
import com.TiendaDisco.RegistroResenas.exception.ManejoErrores;
import com.TiendaDisco.RegistroResenas.mapper.Mapper;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la logica de negocio para las resenas.
 * Contiene las operaciones para registrar, validar y consultar las resenas de los discos.
 * * @author Diego Barria
 * * @author Fernando Castillo
 * * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class ResenaService implements IResenaService {
    @Autowired
    private ResenaRepository repo;

    /**
     * Registra y guarda una nueva resena en la base de datos.
     * * @param r El objeto {@link ResenaDTO} con la informacion a registrar.
     * @return La resena guardada junto con el ID autogenerado por la base de datos.
     */
    public Resena postResena(Resena r){
        return repo.save(r);
    }

    /**
     * Recupera una lista con todas las resenas registrados actualmente en el sistema.
     * Cada resena encontrada es mapeado a su DTO
     * @return Una coleccion {@link List} de objetos {@link ResenaDTO} con el catalogo completo.
     */
    public List<ResenaDTO> getAllResenas(){
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    /**
     * Busca y obtiene la informacion de una resena en base a su id.
     * La respuesta es transformada a un DTO
     * * @param id el id de la resena que se desea buscar
     * @return Un objeto {@link ResenaDTO} con la informacion de la resena solicitado.
     * @throws ManejoErrores Si no existe ninguna resena con el ID proporcionado.
     */
    public ResenaDTO getResenaId(Long id){
        Resena resena = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id no encontrada"));
        return Mapper.toDTO(resena);
    }

    /**
     * Elimina permanentemente una resena en la base de datos mediante su ID.
     * * @param id El identificador unico del disco que se desea eliminar.
     * @return Un mensaje de tipo String confirmando que se elimino la resena.
     * @throws ManejoErrores Si el id a eliminar no existe en la base de datos.
     */
    public String deleteResena(Long id){
        Resena resena = repo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Reseña no encontrada"));
        repo.delete(resena);
        return "Reseña eliminada";
    }
}
