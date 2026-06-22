package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;

import java.util.List;

public interface IAdminService {
    List<UserDTO> getAllUser();

    User postUsuario(User u);

    Admin postAdmin(Admin u);

    UserDTO getUserId(Long id);

    UserDTO getUserName(String name);

    void deleteUserId(Long id);

    User putUser(Long id, User u);

    User putPuntaje(Long id, Integer puntaje);

    List<AdminDTO> getAllAdmin();

    AdminDTO getAdminId(Long id);

    AdminDTO getAdminName(String name);

    void deleteAdminId(Long id);

    Admin putAdmin(Long id, Admin a);
}
