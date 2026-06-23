package com.TiendaDisco.AdministracionUsuario.repository;

import com.TiendaDisco.AdministracionUsuario.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        adminRepository.deleteAll();
    }

    @Test
    void debeBuscarPorUserNameExistente() {
        adminRepository.save(new Admin(null, "Admin1", "admin@mail.com", LocalDate.now(), "pass", true));

        Optional<Admin> resultado = adminRepository.findByUserName("Admin1");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUserName()).isEqualTo("Admin1");
    }

    @Test
    void debeRetornarVacioCuandoUserNameNoExiste() {
        Optional<Admin> resultado = adminRepository.findByUserName("NoExiste");

        assertThat(resultado).isEmpty();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        Admin admin = new Admin(null, "Admin2", "admin2@mail.com", LocalDate.now(), "pass", true);

        Admin guardado = adminRepository.save(admin);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
