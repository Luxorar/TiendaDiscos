package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.Repository.DescuentoRepository;

import com.TiendaDisco.AdministracionDescuentos.client.DiscoClient;
import com.TiendaDisco.AdministracionDescuentos.client.ProductoClient;

import com.TiendaDisco.AdministracionDescuentos.exception.ManejoErrores;

import com.TiendaDisco.AdministracionDescuentos.mapper.Mapper;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacion del servicio de descuentos.
 * <p>Contiene la logica de negocio para gestionar descuentos, incluyendo
 * la asociacion de discos y productos a los mismos.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@Service
public class DescuentoService implements IDescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepo;

    @Autowired
    private DiscoClient discoClient;

    @Autowired
    private ProductoClient productoClient;

    @Autowired
    private Mapper mapper;

    @Override
    public List<DescuentoDTO> getAllDescuentos() {
        return descuentoRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public DescuentoDTO getDescuentoId(Long id) {
        Descuento descuento = descuentoRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado con el ID: " + id));

        return mapper.toDTO(descuento);
    }

    @Override
    public DescuentoDTO getDescuentoNombre(String nombre) {
        Descuento descuento = descuentoRepo.findByNombre(nombre)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado con el nombre: " + nombre));

        return mapper.toDTO(descuento);
    }

    @Override
    public Descuento postDescuento(Descuento d) {
        return descuentoRepo.save(d);
    }

    @Override
    public String putDescuento(Long id, Descuento d) {
        Descuento desc = descuentoRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de descuento a modificar no encontrado"));

        desc.setNombre(d.getNombre());
        desc.setEstado(d.getEstado());
        desc.setDescuento(d.getDescuento());

        descuentoRepo.save(desc);
        return "Descuento modificado exitosamente";
    }

    @Override
    public String deleteDescuento(Long id) {
        Descuento desc = descuentoRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Id de descuento a eliminar no encontrado"));

        descuentoRepo.delete(desc);
        return "Descuento eliminado exitosamente";
    }

    @Override
    public String agregarDisco(String nombreDescuento, Long idDisco) {
        Descuento desc = descuentoRepo.findByNombre(nombreDescuento)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado: " + nombreDescuento));

        try {
            discoClient.obtenerDiscoPorId(idDisco);
        } catch (Exception e) {
            throw new ManejoErrores("Disco no encontrado con el ID: " + idDisco);
        }

        desc.getDiscoIds().add(idDisco);
        descuentoRepo.save(desc);

        return "Disco agregado al descuento exitosamente";
    }

    @Override
    public String quitarDisco(String nombreDescuento, Long idDisco) {
        Descuento desc = descuentoRepo.findByNombre(nombreDescuento)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado: " + nombreDescuento));

        if (!desc.getDiscoIds().remove(idDisco)) {
            throw new ManejoErrores("Disco con ID " + idDisco + " no está asociado al descuento");
        }

        descuentoRepo.save(desc);
        return "Disco eliminado del descuento exitosamente";
    }

    @Override
    public String agregarProducto(String nombreDescuento, Long idProducto) {
        Descuento desc = descuentoRepo.findByNombre(nombreDescuento)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado: " + nombreDescuento));

        try {
            productoClient.obtenerProductoPorId(idProducto);
        } catch (Exception e) {
            throw new ManejoErrores("Producto no encontrado con el ID: " + idProducto);
        }

        desc.getProductoIds().add(idProducto);
        descuentoRepo.save(desc);

        return "Producto agregado al descuento exitosamente";
    }

    @Override
    public String quitarProducto(String nombreDescuento, Long idProducto) {
        Descuento desc = descuentoRepo.findByNombre(nombreDescuento)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado: " + nombreDescuento));

        if (!desc.getProductoIds().remove(idProducto)) {
            throw new ManejoErrores("Producto con ID " + idProducto + " no está asociado al descuento");
        }

        descuentoRepo.save(desc);
        return "Producto eliminado del descuento exitosamente";
    }
}
