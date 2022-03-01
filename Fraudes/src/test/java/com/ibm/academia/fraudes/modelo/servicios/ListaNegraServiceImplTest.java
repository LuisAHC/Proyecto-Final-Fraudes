package com.ibm.academia.fraudes.modelo.servicios;

import com.ibm.academia.fraudes.modelo.repositorios.ListaNegraRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ListaNegraServiceImplTest {
    @MockBean
    private ListaNegraRepository listaNegraRepository;

    @Autowired
    private ListaNegraService listaNegraService;

    @Test
    @DisplayName("Test: Encontrar IP en lista negra")
    void encontrarPorIp() {
        // Given
        Random r = new Random();
        String ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);

        // When
        listaNegraService.encontrarPorIp(ip);

        // Then
        verify(listaNegraRepository).encontrarPorIp(ip);
    }

    @Test
    @DisplayName("Test: Agregar IP a lista negra")
    void banearPorIp() {
        // Given
        Random r = new Random();
        String ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);

        // When
        listaNegraService.banearPorIp(ip);
    }
}