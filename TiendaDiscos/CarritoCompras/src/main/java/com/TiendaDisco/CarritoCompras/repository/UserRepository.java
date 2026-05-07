package com.TiendaDisco.CarritoCompras.repository;

import com.TiendaDisco.CarritoCompras.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {

    ArrayList<User> listaUsers = new ArrayList<>();

    default User postUser(User user){
        listaUsers.add(user);
        return user;
    }

    default User getUserId(Long id){
        for (User user : listaUsers){
            if(id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

    default User deleteUser(Long id){
        for (User user : listaUsers){
            if(id.equals(user.getId())){
                listaUsers.remove(user);
                return user;
            }
        }
        return null;
    }

    default User updateUser(User user, Long id){
        for (User user1 : listaUsers){
            if(id.equals(user1.getId())){
                user.setUserName(user1.getUserName());
                user.setPassword(user1.getPassword());

                return user;
            }
        }
        return null;
    }
}
