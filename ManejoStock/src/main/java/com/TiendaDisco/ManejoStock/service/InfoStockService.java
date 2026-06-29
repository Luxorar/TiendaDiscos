package com.TiendaDisco.ManejoStock.service;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.exception.ManejoErrores;

import com.TiendaDisco.ManejoStock.mapper.Mapper;
import com.TiendaDisco.ManejoStock.model.infoStock;

import com.TiendaDisco.ManejoStock.repository.InfoStockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoStockService implements IInfoStockService {

    @Autowired
    private InfoStockRepository stockRepo;

    @Autowired
    private Mapper mapper;

    //==================REGISTRA INFO DE STOCK================================
    @Override
    public infoStock postInfoStock(infoStock stock) {
        return stockRepo.save(stock);
    }

    //==================OBTIENE STOCK POR SEDE================================
    @Override
    public List<InfoStockDTO> getSedeInfo(String nombreSede) {
        List<InfoStockDTO> lista = stockRepo.findAll().stream()
                .map(mapper::toDTO)
                .filter(dto -> dto.getNombreSede().equals(nombreSede))
                .toList();
        if (lista.isEmpty()) {
            throw new ManejoErrores("No se encontró stock para la sede: " + nombreSede);
        }
        return lista;
    }

    //==================OBTIENE STOCK POR PRODUCTO================================
    @Override
    public InfoStockDTO getProductoInfo(String nombreProducto) {
        return stockRepo.findAll().stream()
                .map(mapper::toDTO)
                .filter(dto -> dto.getNombreProducto().equals(nombreProducto))
                .findFirst()
                .orElseThrow(() -> new ManejoErrores("No se encontró información para el producto: " + nombreProducto));
    }

    //==================OBTIENE STOCK POR ID================================
    @Override
    public InfoStockDTO getInfoID(Long id) {
        infoStock stock = stockRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("ID de stock no encontrado: " + id));
        return mapper.toDTO(stock);
    }

    //==================OBTIENE ENTIDAD STOCK POR ID================================
    private infoStock getInfoStockEntity(Long id) {
        return stockRepo.findById(id)
                .orElseThrow(() -> new ManejoErrores("ID de stock no encontrado: " + id));
    }


    //==================ACTUALIZA STOCK================================
    @Override
    public String putStock(Long id, int nuevoStock) {
        infoStock stock = getInfoStockEntity(id);
        stock.setStockActual(nuevoStock);
        stockRepo.save(stock);
        return "Stock actualizado exitosamente a: " + nuevoStock;
    }


    //==================OBTIENE TODO EL STOCK================================
    @Override
    public List<InfoStockDTO> getAllInfoStock() {
        return stockRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    //==================ELIMINA REGISTRO DE STOCK================================
    @Override
    public String deleteInfo(Long id) {
        infoStock stock = getInfoStockEntity(id);
        stockRepo.delete(stock);
        return "Registro de stock eliminado exitosamente";
    }
}
