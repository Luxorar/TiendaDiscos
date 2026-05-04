package com.TiendaDisco.AdministracionDescuentos.Repository;

import com.TiendaDisco.AdministracionDescuentos.model.Descuento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DescuentoRepository {
    ArrayList<Descuento> ListaDescuentos = new ArrayList<>();

    default List<Descuento> ObtenerTodos() {
        return ListaDescuentos;
    }

    default Descuento ObtenerPorId(long id) {
        for (Descuento d : ListaDescuentos) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    default Descuento ObtenerPorNombre(String nombre) {
        for (Descuento d : ListaDescuentos) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
            }
        }
        return null;
    }

    default boolean eliminar(Long id) {
        for (int i = 0; i < ListaDescuentos.size(); i++) {
            if (ListaDescuentos.get(i).getId().equals(id)) {
                ListaDescuentos.remove(i);
                return true;
            }
        }
        return false;
    }
}