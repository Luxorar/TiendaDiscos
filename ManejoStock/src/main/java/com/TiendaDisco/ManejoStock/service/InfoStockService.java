package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.exception.ManejoErrores;

import com.TiendaDisco.ManejoStock.mapper.Mapper;
import com.TiendaDisco.ManejoStock.model.Sede;
import com.TiendaDisco.ManejoStock.model.infoStock;

import com.TiendaDisco.ManejoStock.repository.InfoStockRepository;
import com.TiendaDisco.ManejoStock.repository.SedeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoStockService implements IInfoStockService {

    @Autowired
    private InfoStockRepository stockRepo;

    @Autowired
    private SedeRepository sedeRepo;

    @Override
    public infoStock postInfoStock(infoStock stock) {
        return stockRepo.save(stock);
    }

    @Override
    public List<InfoStockDTO> getSedeInfo(String nombreSede) {
        List<infoStock> lista = stockRepo.findBySede_NombreSede(nombreSede);
        if(lista.isEmpty()){
            throw new ManejoErrores("No se encontró stock para la sede: " + nombreSede);
        }
        return lista.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public InfoStockDTO getProductoInfo(String nombreProducto) {
        infoStock stock = stockRepo.findByNombreProducto(nombreProducto)
                .orElseThrow(() -> new ManejoErrores("No se encontró información para el producto: " + nombreProducto));
        return Mapper.toDTO(stock);
    }

    @Override
    public InfoStockDTO getInfoID(Long id) {
        infoStock stock = stockRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("ID de stock no encontrado: " + id));
        return Mapper.toDTO(stock);
    }

    private infoStock getInfoStockEntity(Long id) {
        return stockRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("ID de stock no encontrado: " + id));
    }

    @Override
    public String putNombreProducto(Long id, String nuevoNombre) {
        infoStock stock = getInfoStockEntity(id);
        stock.setNombreProducto(nuevoNombre);
        stockRepo.save(stock);
        return "Nombre del producto actualizado exitosamente a: " + nuevoNombre;
    }

    @Override
    public String putStock(Long id, int nuevoStock) {
        infoStock stock = getInfoStockEntity(id);
        stock.setStockActual(nuevoStock);
        stockRepo.save(stock);
        return "Stock actualizado exitosamente a: " + nuevoStock;
    }

    @Override
    public String putSede(Long id, String nombreSede) {
        infoStock stock = getInfoStockEntity(id);

        Sede nuevaSede = sedeRepo.findByNombreSede(nombreSede)
                .orElseThrow(() -> new ManejoErrores("La sede especificada no existe: " + nombreSede));

        stock.setSede(nuevaSede);
        stockRepo.save(stock);
        return "Sede del producto actualizada exitosamente a: " + nombreSede;
    }

    @Override
    public List<InfoStockDTO> getAllInfoStock() {
        return stockRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public String deleteInfo(Long id) {
        infoStock stock = getInfoStockEntity(id);
        stockRepo.delete(stock);
        return "Registro de stock eliminado exitosamente";
    }
}
