package com.TiendaDisco.AdministracionUsuario.service;

import com.TiendaDisco.AdministracionUsuario.DTO.UserDTO;
import com.TiendaDisco.AdministracionUsuario.exception.ManejoErrores;
import com.TiendaDisco.AdministracionUsuario.model.User;
import com.TiendaDisco.AdministracionUsuario.repository.AdminRepository;
import com.TiendaDisco.AdministracionUsuario.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    private User createTestUser(Long id, String userName, Integer puntos, Boolean cuentaActiva) {
        return User.builder()
                .id(id)
                .userName(userName)
                .gmail("test@test.com")
                .fechaRegistro(LocalDate.of(2025, 1, 15))
                .puntos(puntos)
                .contrasena("pass123")
                .cuentaActiva(cuentaActiva)
                .build();
    }

    private User createTestUser(Long id) {
        return createTestUser(id, "testuser", 100, true);
    }

    @Test
    void getAllUser_ShouldReturnAllUsers() {
        List<User> users = List.of(
                createTestUser(1L, "user1", 50, true),
                createTestUser(2L, "user2", 100, true)
        );
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = adminService.getAllUser();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUserName()).isEqualTo("user1");
        assertThat(result.get(1).getUserName()).isEqualTo("user2");
        verify(userRepository).findAll();
    }

    @Test
    void postUsuario_ShouldReturnSavedUser() {
        User user = createTestUser(null, "newuser", 0, false);
        User saved = createTestUser(1L, "newuser", 0, true);
        when(userRepository.save(user)).thenReturn(saved);

        User result = adminService.postUsuario(user);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getCuentaActiva()).isTrue();
        verify(userRepository).save(user);
    }

    @Test
    void getUserId_ShouldReturnUserDTO() {
        User user = createTestUser(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = adminService.getUserId(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUserName()).isEqualTo("testuser");
        assertThat(result.getPuntos()).isEqualTo(100);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserId_ShouldThrowWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.getUserId(99L))
                .isInstanceOf(ManejoErrores.class);
        verify(userRepository).findById(99L);
    }

    @Test
    void getUserName_ShouldReturnUserDTO() {
        User user = createTestUser(1L);
        when(userRepository.findByUserName("testuser")).thenReturn(Optional.of(user));

        UserDTO result = adminService.getUserName("testuser");

        assertThat(result.getUserName()).isEqualTo("testuser");
        verify(userRepository).findByUserName("testuser");
    }

    @Test
    void getUserName_ShouldThrowWhenNotFound() {
        when(userRepository.findByUserName("no")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.getUserName("no"))
                .isInstanceOf(ManejoErrores.class);
        verify(userRepository).findByUserName("no");
    }

    @Test
    void deleteUserId_ShouldDeleteWhenExists() {
        User user = createTestUser(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        adminService.deleteUserId(1L);

        verify(userRepository).findById(1L);
        verify(userRepository).delete(user);
    }

    @Test
    void deleteUserId_ShouldThrowWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.deleteUserId(99L))
                .isInstanceOf(ManejoErrores.class);
        verify(userRepository).findById(99L);
        verify(userRepository, never()).delete(any());
    }

    @Test
    void putUser_ShouldUpdateUser() {
        User existing = createTestUser(1L, "oldname", 50, true);
        User updates = User.builder().userName("newname").puntos(200).build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(existing);

        User result = adminService.putUser(1L, updates);

        assertThat(result.getUserName()).isEqualTo("newname");
        assertThat(result.getPuntos()).isEqualTo(200);
        verify(userRepository).findById(1L);
        verify(userRepository).save(existing);
    }

    @Test
    void putUser_ShouldThrowWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.putUser(99L, mock(User.class)))
                .isInstanceOf(ManejoErrores.class);
        verify(userRepository).findById(99L);
        verify(userRepository, never()).save(any());
    }

    @Test
    void putPuntaje_ShouldUpdatePuntaje() {
        User user = createTestUser(1L, "test", 50, true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User result = adminService.putPuntaje(1L, 999);

        assertThat(result.getPuntos()).isEqualTo(999);
        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test
    void putPuntaje_ShouldThrowWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.putPuntaje(99L, 100))
                .isInstanceOf(ManejoErrores.class);
        verify(userRepository).findById(99L);
        verify(userRepository, never()).save(any());
    }
}
