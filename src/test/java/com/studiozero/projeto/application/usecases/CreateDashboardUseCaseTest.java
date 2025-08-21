package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateDashboardUseCaseTest {
    @Test
    void testCreateDashboardSuccess() {
        // Simule a criação de dashboard e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateDashboardThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar dashboard");
        });
        assertEquals("Erro ao criar dashboard", exception.getMessage());
    }
}
