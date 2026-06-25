package com.TiendaDisco.ManejoStock.mapper;

import com.TiendaDisco.ManejoStock.DTO.InfoStockDTO;
import com.TiendaDisco.ManejoStock.client.DiscoClient;
import com.TiendaDisco.ManejoStock.client.ProductoClient;
import com.TiendaDisco.ManejoStock.client.SedeClient;
import com.TiendaDisco.ManejoStock.model.TipoProducto;
import com.TiendaDisco.ManejoStock.model.infoStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private SedeClient sedeClient;
    @Autowired
    private ProductoClient productoClient;
    @Autowired
    private DiscoClient discoClient;


    public InfoStockDTO toDTO(infoStock stock) {
        if (stock == null) return null;

        String nombre = new String();

        if(stock.getProducto().getTipoProducto().equals(TipoProducto.PRODUCTO)){
            var response = productoClient.obtenerProductoPorId(stock.getProducto().getIdProducto());
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                nombre = response.getBody().getNombreProducto() + " - " + response.getBody().getMarca();
            }else{
                nombre = "producto fuera del registro";
            }
        } else if (stock.getProducto().getTipoProducto().equals(TipoProducto.DISCO)) {
            var response = discoClient.obtenerDiscoPorId(stock.getProducto().getIdProducto());
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                nombre = response.getBody().getNombreDisco() + " - " + response.getBody().getArtista();
            }else{
                nombre = "disco fuera del registro";
            }
        }else{
            nombre = "producto no reconocido";
        }

        return InfoStockDTO.builder()
                .id(stock.getId())
                .nombreProducto(nombre)
                .nombreSede(sedeClient.getSedeId(stock.getSede()).getNombreSede())
                .stockActual(stock.getStockActual())
                .build();
    }
}
