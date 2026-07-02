package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementacion del servicio de carrito de compras.
 * <p>Contiene la logica de negocio para gestionar carritos de usuarios,
 * incluyendo la creacion, consulta, actualizacion de descuentos y
 * eliminacion de carritos.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class CarritoService implements ICarritoService{
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<CarritoDTO> getListaCarrito() {
        return carritoRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public Carrito postCarrito(Carrito c) {
        return carritoRepository.save(c);
    }

    @Override
    public CarritoDTO getCarrito(Long usuario) {
        Carrito carro = carritoRepository.findByUserId(usuario)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));

        return mapper.toDTO(carro);
    }


    @Override
    public String updateCarrito(Carrito c, Long usuario) {
        Carrito carro = carritoRepository.findByUserId(usuario)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + usuario));

        carro.setDescuento(c.getDescuento());
        carritoRepository.save(carro);
        return "Carrito actualizado";
    }

    @Override
    public void deleteCarrito(Long usuario) {
        Carrito carro = carritoRepository.findByUserId(usuario)
                .orElseThrow(() -> new ManejoErrores("Carrito no encontrado para el usuario: " + usuario));
        carritoRepository.delete(carro);
    }
}
