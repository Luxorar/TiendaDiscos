package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.AdminDTO;
import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.exception.ManejoErrores;
import com.TiendaDisco.AdministracionUsuario.model.Admin;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.repository.AdminRepository;
import com.TiendaDisco.AdministracionUsuario.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    //---------------------------------USUARIO----------------------------------

    //---------------------------------USUARIO----------------------------------

    @Test
    void deberiaCrearUsuario() {
        User entrada = new User(null, "userEjemplo", "ejemplo@gmail.com",
                null, 100, "123456", true, BigDecimal.ZERO, false, null, null);
        User guardado = new User(1L, "userEjemplo", "ejemplo@gmail.com",
                null, 100, "123456", true, BigDecimal.ZERO, false, null, null);

        when(userRepository.save(any(User.class))).thenReturn(guardado);

        User resultado = adminService.postUsuario(entrada);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getUserName()).isEqualTo("userEjemplo");
        assertThat(resultado.getPuntos()).isEqualTo(100);
        assertThat(resultado.getContrasena()).isEqualTo("123456");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void debeRetornarTodosLosUsuarios() {
        User u1 = new User(1L, "Ana", "ana@mail.com", null, 100, "pass", true, BigDecimal.ZERO, false, null, null);
        User u2 = new User(2L, "Luis", "luis@mail.com", null, 50, "pass", true, BigDecimal.ZERO, false, null, null);
        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        List<UserDTO> resultado = adminService.getAllUser();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getUserName()).isEqualTo("Ana");
        assertThat(resultado.get(1).getUserName()).isEqualTo("Luis");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void debeRetornarUsuarioPorNombre() {
        User user = new User(1L, "Ana", "ana@mail.com", null, 100, "pass", true, BigDecimal.ZERO, false, null, null);
        when(userRepository.findByUserName("Ana")).thenReturn(Optional.of(user));

        UserDTO resultado = adminService.getUserName("Ana");

        assertThat(resultado.getUserName()).isEqualTo("Ana");
        verify(userRepository, times(1)).findByUserName("Ana");
    }

    @Test
    void debeRetornarNullCuandoCuentaUsuarioInactiva() {
        User user = new User(1L, "Inactivo", "inactivo@mail.com", null, 100, "pass", false, BigDecimal.ZERO, false, null, null);
        when(userRepository.findByUserName("Inactivo")).thenReturn(Optional.of(user));

        UserDTO resultado = adminService.getUserName("Inactivo");

        assertThat(resultado).isNull();
        verify(userRepository, times(1)).findByUserName("Inactivo");
    }

    @Test
    void debeLanzarExcepcionCuandoNombreUsuarioNoExiste() {
        when(userRepository.findByUserName("No existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.getUserName("No existe"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No se encontró un usuario con ese nombre");
    }

    @Test
    void debeActualizarUsuarioConTodosLosCampos() {
        User existente = new User(1L, "Original", "orig@mail.com", null, 100, "pass", false, BigDecimal.ZERO, false, null, null);
        User nuevos = new User(null, "Modificado", null, null, 200, "nuevaPass", true, BigDecimal.ZERO, false, null, null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(userRepository.save(any(User.class))).thenReturn(existente);

        User resultado = adminService.putUser(1L, nuevos);

        assertThat(resultado.getUserName()).isEqualTo("Modificado");
        assertThat(resultado.getPuntos()).isEqualTo(200);
        assertThat(resultado.getContrasena()).isEqualTo("nuevaPass");
        assertThat(resultado.getCuentaActiva()).isTrue();
        verify(userRepository, times(1)).save(existente);
    }

    @Test
    void debeActualizarUsuarioSinModificarNullos() {
        User existente = new User(1L, "Conservar", "correo@mail.com", null, 50, "pass", true, BigDecimal.ZERO, false, null, null);
        User nuevos = new User(null, null, null, null, null, null, null, null, null, null, null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(userRepository.save(any(User.class))).thenReturn(existente);

        User resultado = adminService.putUser(1L, nuevos);

        assertThat(resultado.getUserName()).isEqualTo("Conservar");
        assertThat(resultado.getPuntos()).isEqualTo(50);
        assertThat(resultado.getContrasena()).isEqualTo("pass");
        assertThat(resultado.getCuentaActiva()).isTrue();
        verify(userRepository, times(1)).save(existente);
    }

    @Test
    void debeLanzarExcepcionCuandoIdUsuarioModificarNoExiste() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.putUser(99L, new User()))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("id");
    }

    @Test
    void debeActualizarPuntaje() {
        User existente = new User(1L, "Puntos", "puntos@mail.com", null, 50, "pass", true, BigDecimal.ZERO, false, null, null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(userRepository.save(any(User.class))).thenReturn(existente);

        User resultado = adminService.putPuntaje(1L, 200);

        assertThat(resultado.getPuntos()).isEqualTo(200);
        verify(userRepository, times(1)).save(existente);
    }

    @Test
    void debeLanzarExcepcionCuandoIdPuntajeNoExiste() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.putPuntaje(99L, 100))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("id");
    }

    @Test
    void debeEliminarUsuario() {
        User user = new User(1L, "Eliminar", "del@mail.com", null, 50, "pass", true, BigDecimal.ZERO, false, null, null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        adminService.deleteUserId(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void debeLanzarExcepcionCuandoIdEliminarNoExiste() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.deleteUserId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No se encontró un usuario con ese nombre");
    }

    //---------------------------------ADMIN------------------------------------

    @Test
    void deberiaCrearAdministrador() {
        Admin entrada = new Admin(null, "adminEjemplo", "admin@mail.com",
                null, "123456", true);
        Admin guardado = new Admin(1L, "adminEjemplo", "admin@mail.com",
                null, "123456", true);

        when(adminRepository.save(any(Admin.class))).thenReturn(guardado);

        Admin resultado = adminService.postAdmin(entrada);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getUserName()).isEqualTo("adminEjemplo");
        verify(adminRepository).save(any(Admin.class));
    }

    @Test
    void debeRetornarTodosLosAdministradores() {
        Admin admin1 = new Admin(1L, "Admin Uno", "admin1@mail.com",
                null, "pass1", true);
        Admin admin2 = new Admin(2L, "Admin Dos", "admin2@mail.com",
                null, "pass2", true);

        when(adminRepository.findAll()).thenReturn(List.of(admin1, admin2));

        List<AdminDTO> resultado = adminService.getAllAdmin();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getUserName()).isEqualTo("Admin Uno");
        assertThat(resultado.get(1).getUserName()).isEqualTo("Admin Dos");
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    void debeRetornarAdminCuandoExiste() {
        Admin admin = new Admin(1L, "Admin Test", "test@mail.com",
                null, "pass", true);
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        AdminDTO resultado = adminService.getAdminId(1L);

        assertThat(resultado.getUserName()).isEqualTo("Admin Test");
        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoAdminIdNoExiste() {
        when(adminRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.getAdminId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("99");
    }

    @Test
    void debeRetornarAdminPorNombreCuandoExiste() {
        Admin admin = new Admin(1L, "Admin Nombre", "nombre@mail.com",
                null, "pass", true);
        when(adminRepository.findByUserName("Admin Nombre")).thenReturn(Optional.of(admin));

        AdminDTO resultado = adminService.getAdminName("Admin Nombre");

        assertThat(resultado.getUserName()).isEqualTo("Admin Nombre");
        verify(adminRepository, times(1)).findByUserName("Admin Nombre");
    }

    @Test
    void debeLanzarExcepcionCuandoNombreAdminNoExiste() {
        when(adminRepository.findByUserName("No existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.getAdminName("No existe"))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("No existe");
    }

    @Test
    void debeActualizarAdmin() {
        Admin adminExistente = new Admin(1L, "Original", "orig@mail.com",
                null, "pass", true);
        Admin nuevosDatos = new Admin(null, "Modificado", null,
                null, "nuevaPass", false);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(adminExistente));
        when(adminRepository.save(any(Admin.class))).thenReturn(adminExistente);

        Admin resultado = adminService.putAdmin(1L, nuevosDatos);

        assertThat(resultado.getUserName()).isEqualTo("Modificado");
        assertThat(resultado.getContrasena()).isEqualTo("nuevaPass");
        assertThat(resultado.getCuentaActiva()).isFalse();
        verify(adminRepository, times(1)).save(adminExistente);
    }

    @Test
    void debeActualizarAdminSinModificarNullos() {
        Admin existente = new Admin(1L, "Conservar", "correo@mail.com",
                null, "pass", true);
        Admin nuevos = new Admin(null, null, null, null, null, null);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(adminRepository.save(any(Admin.class))).thenReturn(existente);

        Admin resultado = adminService.putAdmin(1L, nuevos);

        assertThat(resultado.getUserName()).isEqualTo("Conservar");
        assertThat(resultado.getContrasena()).isEqualTo("pass");
        assertThat(resultado.getCuentaActiva()).isTrue();
        verify(adminRepository, times(1)).save(existente);
    }

    @Test
    void debeLanzarExcepcionCuandoIdModificacionAdminNoExiste() {
        when(adminRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.putAdmin(99L, new Admin()))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("99");
    }

    @Test
    void debeEliminarAdmin() {
        Admin admin = new Admin(1L, "Eliminar", "del@mail.com",
                null, "pass", true);
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        adminService.deleteAdminId(1L);

        verify(adminRepository, times(1)).delete(admin);
    }

    @Test
    void debeLanzarExcepcionCuandoIdEliminacionAdminNoExiste() {
        when(adminRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.deleteAdminId(99L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("99");
    }
}
