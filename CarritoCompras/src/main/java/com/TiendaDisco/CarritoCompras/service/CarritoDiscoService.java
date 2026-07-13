package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.client.DiscoClient;
import com.TiendaDisco.CarritoCompras.dto.CarritoDiscoDTO;
import com.TiendaDisco.CarritoCompras.dto.DiscoDTO;
import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.model.CarritoDisco;
import com.TiendaDisco.CarritoCompras.repository.CarritoDiscoRepository;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoDiscoService implements ICarritoDiscoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoDiscoRepository carritoDiscoRepository;

    @Autowired
    private DiscoClient discoClient;

    @Override
    @Transactional(readOnly = true)
    public List<CarritoDiscoDTO> getListaDiscos(Long user) {
        Carrito carrito = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        List<CarritoDiscoDTO> result = new ArrayList<>();
        for (CarritoDisco cd : carrito.getDiscosAgregados()) {
            result.add(buildDTO(cd));
        }
        return result;
    }

    @Override
    public CarritoDiscoDTO addDisco(Long user, Long discoId) {
        Carrito carrito = carritoRepository.findByUserId(user)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + user));

        Optional<CarritoDisco> existing = carritoDiscoRepository
                .findByCarritoUserIdAndDiscoId(user, discoId);

        if (existing.isPresent()) {
            CarritoDisco cd = existing.get();
            cd.setQty(cd.getQty() + 1);
            carritoDiscoRepository.save(cd);
            return buildDTO(cd);
        }

        CarritoDisco nuevo = CarritoDisco.builder()
                .discoId(discoId)
                .qty(1)
                .carrito(carrito)
                .build();
        carritoDiscoRepository.save(nuevo);
        carrito.getDiscosAgregados().add(nuevo);
        carritoRepository.save(carrito);
        return buildDTO(nuevo);
    }

    @Override
    public CarritoDiscoDTO updateQty(Long user, Long discoId, int qty) {
        CarritoDisco cd = carritoDiscoRepository.findByCarritoUserIdAndDiscoId(user, discoId)
                .orElseThrow(() -> new ManejoErrores("Disco no encontrado en el carrito"));

        if (qty <= 0) {
            carritoDiscoRepository.delete(cd);
            return null;
        }

        cd.setQty(qty);
        carritoDiscoRepository.save(cd);
        return buildDTO(cd);
    }

    @Override
    public String deleteDisco(Long user, Long discoId) {
        CarritoDisco cd = carritoDiscoRepository.findByCarritoUserIdAndDiscoId(user, discoId)
                .orElseThrow(() -> new ManejoErrores("Disco no encontrado en el carrito"));

        if (cd.getQty() > 1) {
            cd.setQty(cd.getQty() - 1);
            carritoDiscoRepository.save(cd);
            return "Cantidad decrementada";
        }

        carritoDiscoRepository.delete(cd);
        return "Disco eliminado del carrito";
    }

    @Override
    public void clearDiscos(Long user) {
        List<CarritoDisco> discos = carritoDiscoRepository.findByCarritoUserId(user);
        carritoDiscoRepository.deleteAll(discos);
    }

    private CarritoDiscoDTO buildDTO(CarritoDisco cd) {
        try {
            DiscoDTO disco = discoClient.obtenerDiscoPorId(cd.getDiscoId()).getBody();
            if (disco != null) {
                return CarritoDiscoDTO.builder()
                        .id(cd.getId())
                        .discoId(cd.getDiscoId())
                        .qty(cd.getQty())
                        .nombreDisco(disco.getNombreDisco())
                        .artista(disco.getArtista())
                        .precio(disco.getPrecio())
                        .imagen(disco.getImagen())
                        .build();
            }
        } catch (Exception e) {
            // servicio caído
        }
        return CarritoDiscoDTO.builder()
                .id(cd.getId())
                .discoId(cd.getDiscoId())
                .qty(cd.getQty())
                .nombreDisco("Disco #" + cd.getDiscoId())
                .artista("")
                .precio(0)
                .imagen(null)
                .build();
    }
}
