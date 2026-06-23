package com.TiendaDisco.AdministracionDescuentos.mapper;

import com.TiendaDisco.AdministracionDescuentos.DTO.DescuentoDTO;
import com.TiendaDisco.AdministracionDescuentos.client.DiscoClient;
import com.TiendaDisco.AdministracionDescuentos.client.ProductoClient;
import com.TiendaDisco.AdministracionDescuentos.model.Descuento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    @Autowired
    private DiscoClient discoClient;

    @Autowired
    private ProductoClient productoClient;

    public DescuentoDTO toDTO(Descuento descuento){
        if(descuento == null) return null;

        List<String> listaDisco = new ArrayList<>();
        for (Long id : descuento.getDiscoIds()) {
            var response = discoClient.obtenerDiscoPorId(id);
            if (response.getBody() != null) {
                listaDisco.add(response.getBody().getArtista() + " - " + response.getBody().getNombreDisco());
            }
        }

        List<String> listaP = new ArrayList<>();
        for (Long id : descuento.getProductoIds()) {
            var response = productoClient.obtenerProductoPorId(id);
            if (response.getBody() != null) {
                listaP.add(response.getBody().getNombreProducto() + " - " + response.getBody().getMarca());
            }
        }

        return DescuentoDTO.builder()
                .id(descuento.getId())
                .nombre(descuento.getNombre())
                .estado(descuento.getEstado())
                .descuento(descuento.getDescuento())
                .discosAgregados(listaDisco)
                .productosAgregados(listaP)
                .build();
    }
}
