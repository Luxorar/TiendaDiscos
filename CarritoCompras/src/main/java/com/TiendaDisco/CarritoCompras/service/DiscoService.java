package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.Disco;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementacion del servicio de discos del carrito.
 * Contiene la logica de negocio para gestionar discos dentro del carrito.
 */
@Service
@Transactional
public class DiscoService implements IDiscoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private DiscoRepository discoRepository;

    /**
     * Obtiene todos los discos registrados.
     *
     * @return lista de discos
     */
    @Override
    public List<Disco> getAllDiscos() {
        return discoRepository.findAll();
    }

    /**
     * Obtiene la lista de discos del carrito de un usuario.
     *
     * @param user identificador del usuario
     * @return lista de discos en el carrito
     */
    @Override
    public List<Disco> getListaDiscos(Long user) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));
        return carro.getDiscosAgregados();
    }

    /**
     * Agrega un nuevo disco al carrito de un usuario.
     *
     * @param user     identificador del usuario
     * @param idDisco  identificador del disco
     * @param newDisco datos del disco a agregar
     * @return el disco persistido
     */
    @Override
    public Disco postDisco(Long user, Long idDisco, Disco newDisco) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Disco saved = discoRepository.save(newDisco);
        carro.getDiscosAgregados().add(saved);
        carritoRepository.save(carro);
        return saved;
    }

    /**
     * Obtiene un disco especifico del carrito de un usuario.
     *
     * @param user    identificador del usuario
     * @param idDisco identificador del disco
     * @return el disco solicitado
     */
    @Override
    public Disco getDisco(Long user, Long idDisco) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        return carro.getDiscosAgregados().stream()
                .filter(d -> d.getId().equals(idDisco))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Disco no encontrado en el carrito"));
    }

    /**
     * Elimina un disco del carrito de un usuario.
     *
     * @param user    identificador del usuario
     * @param idDisco identificador del disco
     * @return mensaje de confirmacion
     */
    @Override
    public String deleteDiscos(Long user, Long idDisco) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Disco disc = carro.getDiscosAgregados().stream()
                .filter(d -> d.getId().equals(idDisco))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Disco no encontrado en el carrito"));

        carro.getDiscosAgregados().remove(disc);
        carritoRepository.save(carro);
        discoRepository.delete(disc);
        return "Disco eliminado del carrito";
    }

    /**
     * Modifica un disco existente en el carrito de un usuario.
     *
     * @param user  identificador del usuario
     * @param disco objeto con los datos actualizados
     * @return el disco modificado
     */
    @Override
    public Disco putDisco(Long user, Disco disco) {
        Carrito carro = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Disco existente = carro.getDiscosAgregados().stream()
                .filter(d -> d.getId().equals(disco.getId()))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("Disco no encontrado en el carrito"));

        existente.setNombreDisco(disco.getNombreDisco());
        existente.setArtista(disco.getArtista());
        existente.setPrecio(disco.getPrecio());

        discoRepository.save(existente);
        carritoRepository.save(carro);
        return existente;
    }
}
