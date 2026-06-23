package com.TiendaDisco.CarritoCompras.service;

import com.TiendaDisco.CarritoCompras.exception.ManejoErrores;
import com.TiendaDisco.CarritoCompras.model.User;
import com.TiendaDisco.CarritoCompras.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers_returnsAllUsers() {
        User user = new User();
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(1).contains(user);
        verify(userRepository).findAll();
    }

    @Test
    void postUser_savesAndReturnsUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.postUser(user);

        assertThat(result).isSameAs(user);
        verify(userRepository).save(user);
    }

    @Test
    void getUserId_whenFound_returnsUser() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserId(1L);

        assertThat(result).isSameAs(user);
    }

    @Test
    void getUserId_whenNotFound_throwsManejoErrores() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserId(999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Usuario no encontrado");
    }

    @Test
    void deleteUser_whenFound_deletesAndReturnsUser() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.deleteUser(1L);

        assertThat(result).isSameAs(user);
        verify(userRepository).delete(user);
    }

    @Test
    void deleteUser_whenNotFound_throwsManejoErrores() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteUser(999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Usuario no encontrado");
        verify(userRepository, never()).delete(any());
    }

    @Test
    void updateUser_whenFound_updatesFields() {
        User existente = new User();
        existente.setId(1L);
        existente.setUserName("old");
        existente.setGmail("old@test.com");
        existente.setPassword("oldpass");

        User update = new User();
        update.setUserName("new");
        update.setGmail("new@test.com");
        update.setPassword("newpass");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(userRepository.save(existente)).thenReturn(existente);

        User result = userService.updateUser(update, 1L);

        assertThat(result.getUserName()).isEqualTo("new");
        assertThat(result.getGmail()).isEqualTo("new@test.com");
        assertThat(result.getPassword()).isEqualTo("newpass");
        verify(userRepository).save(existente);
    }

    @Test
    void updateUser_partialUpdate_keepsExistingFields() {
        User existente = new User();
        existente.setId(1L);
        existente.setUserName("old");
        existente.setGmail("old@test.com");
        existente.setPassword("oldpass");

        User update = new User();
        update.setUserName("new");
        update.setGmail(null);
        update.setPassword(null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(userRepository.save(existente)).thenReturn(existente);

        User result = userService.updateUser(update, 1L);

        assertThat(result.getUserName()).isEqualTo("new");
        assertThat(result.getGmail()).isEqualTo("old@test.com");
        assertThat(result.getPassword()).isEqualTo("oldpass");
        verify(userRepository).save(existente);
    }

    @Test
    void updateUser_whenNotFound_throwsManejoErrores() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(new User(), 999L))
                .isInstanceOf(ManejoErrores.class)
                .hasMessageContaining("Usuario no encontrado");
        verify(userRepository, never()).save(any());
    }
}
