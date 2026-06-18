package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.exception.ManejoErrores;

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
    public List<infoStock> getSedeInfo(String nombreSede) {
        List<infoStock> lista = stockRepo.findBySede_NombreSede(nombreSede);
        if(lista.isEmpty()){
            throw new ManejoErrores("No se encontró stock para la sede: " + nombreSede);
        }
        return lista;
    }

    @Override
    public infoStock getProductoInfo(String nombreProducto) {
        return stockRepo.findByNombreProducto(nombreProducto)
                .orElseThrow(() -> new ManejoErrores("No se encontró información para el producto: " + nombreProducto));
    }

    @Override
    public infoStock getInfoID(Long id) {
        return stockRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("ID de stock no encontrado: " + id));
    }

    @Override
    public String putNombreProducto(Long id, String nuevoNombre) {
        infoStock stock = getInfoID(id);
        stock.setNombreProducto(nuevoNombre);
        stockRepo.save(stock);
        return "Nombre del producto actualizado exitosamente a: " + nuevoNombre;
    }

    @Override
    public String putStock(Long id, int nuevoStock) {
        infoStock stock = getInfoID(id);
        stock.setStockActual(nuevoStock);
        stockRepo.save(stock);
        return "Stock actualizado exitosamente a: " + nuevoStock;
    }

    @Override
    public String putSede(Long id, String nombreSede) {
        infoStock stock = getInfoID(id);

        Sede nuevaSede = sedeRepo.findByNombreSede(nombreSede)
                .orElseThrow(() -> new ManejoErrores("La sede especificada no existe: " + nombreSede));

        stock.setSede(nuevaSede);
        stockRepo.save(stock);
        return "Sede del producto actualizada exitosamente a: " + nombreSede;
    }

    @Override
    public List<infoStock> getAllInfoStock() {
        return stockRepo.findAll();
    }

    @Override
    public String deleteInfo(Long id) {
        infoStock stock = getInfoID(id);
        stockRepo.delete(stock);
        return "Registro de stock eliminado exitosamente";
    }
}