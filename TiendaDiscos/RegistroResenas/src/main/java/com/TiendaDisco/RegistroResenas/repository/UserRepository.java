package com.TiendaDisco.RegistroResenas.repository;

import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {

    ArrayList<User> listaUsuarios= new ArrayList<>();

    default User postUsuario(User u){
        listaUsuarios.add(u);
        return u;
    }

    default User getUserId(Long id){
        for(User u: listaUsuarios) {
            if (id == u.getId()) {
                return u;
            }
        }
        return null;
    }

    default String putUsers(Long id, User u){
        for(User user: listaUsuarios){
            if(user.getId()==id){
                user.setUserName(u.getUserName());
                user.setGmail(u.getGmail());

                return "Actualizacion exitosa";
            }
        }
        return "Los cmabios no se han realizado";
    }

    default String deleteUser(Long id){
        for(User u: listaUsuarios) {
            if(u.getId() == id){
                listaUsuarios.remove(u);
                return "Usuario eliminado";
            }
        }
        return "Usuario no encontrado";
    }
}
