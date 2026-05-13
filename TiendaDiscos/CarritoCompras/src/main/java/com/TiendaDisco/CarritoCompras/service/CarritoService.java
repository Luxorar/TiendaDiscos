package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.dto.CarritoDTO;
import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.Carrito;
import com.TiendaDisco.CarritoCompras.repository.CarritoRepository;
import com.TiendaDisco.CarritoCompras.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService implements ICarritoService{
    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public List<CarritoDTO> getListaCarrito() {
        return carritoRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public Carrito postCarrito(Carrito c) {
        return null;
    }

    @Override
    public CarritoDTO getCarrito(String usuario) {
        Carrito carro = carritoRepository.findByUserUserName(usuario)
                .orElseThrow(() -> new ManejoErrores("Usuario no encontrado"));

        return Mapper.toDTO(carro);
    }

    @Override
    public String updateCarrito(Carrito c, String usuario) {
        return "";
    }
}
