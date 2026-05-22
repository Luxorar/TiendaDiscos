package com.TiendaDisco.AdministracionDescuentos.service;

import com.TiendaDisco.AdministracionDescuentos.Repository.DescuentoRepository;
import com.TiendaDisco.AdministracionDescuentos.Repository.DiscoRepository;

import com.TiendaDisco.AdministracionDescuentos.exception.ManejoErrores;

import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import com.TiendaDisco.AdministracionDescuentos.model.Disco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescuentoService implements IDescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepo;

    @Autowired
    private DiscoRepository discoRepo;

    @Override
    public List<Descuento> getAllDescuentos() {
        return descuentoRepo.findAll();
    }

    @Override
    public Descuento getDescuentoId(Long id) {
        return descuentoRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado con el ID: " + id));
    }

    @Override
    public Descuento getDescuentoNombre(String nombre) {
        return descuentoRepo.findByNombre(nombre)
                .orElseThrow(() -> new ManejoErrores("Descuento no encontrado con el nombre: " + nombre));
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

        Disco disco = discoRepo.findById(idDisco)
                .orElseThrow(() -> new ManejoErrores("Disco no encontrado con el ID: " + idDisco));

        desc.getDiscosAgregados().add(disco);
        descuentoRepo.save(desc);

        return "Disco agregado al descuento exitosamente";
    }
}