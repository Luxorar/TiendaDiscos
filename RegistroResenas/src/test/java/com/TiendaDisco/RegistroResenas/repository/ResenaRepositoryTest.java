package com.TiendaDisco.RegistroResenas.repository;

import com.TiendaDisco.RegistroResenas.model.Disco;
import com.TiendaDisco.RegistroResenas.model.Resena;
import com.TiendaDisco.RegistroResenas.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ResenaRepositoryTest {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @BeforeEach
    void setUp() {
        resenaRepository.deleteAll();
        userRepository.deleteAll();
        discoRepository.deleteAll();
    }

    @Test
    void debeGuardarYAsignarIdAutomaticamente() {
        User user = userRepository.save(User.builder().userName("Ana").gmail("ana@mail.com").build());
        Disco disco = discoRepository.save(Disco.builder().nombreDisco("Thriller").artista("Michael Jackson").build());
        Resena resena = Resena.builder()
                .user(user)
                .disco(disco)
                .mensaje("Excelente disco")
                .build();

        Resena guardado = resenaRepository.save(resena);

        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isPositive();
    }
}
