package com.TiendaDisco.RegistroResenas.repository;
import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResenaRepository {
    ArrayList<Resena> listaResenas = new ArrayList<>();

    ArrayList<Disco> listaDiscos = new ArrayList<>();

    ArrayList<User> listaUsers = new ArrayList<>();


}
