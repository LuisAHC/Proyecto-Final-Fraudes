package com.ibm.academia.fraudes.modelo.servicios;

import com.ibm.academia.fraudes.modelo.dto.PaisDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class IpServiceFeignTest {
    @Autowired
    private IpService ipService;

    @Test
    @DisplayName("Test: Obtener información de una IP")
    void obtenerInfoIp() {
        // Given
        Random r = new Random();
        String ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
        // When
        PaisDTO expected = ipService.obtenerInfoIp(ip);
        // Then
        assertThat(expected).isNotNull();
    }
}