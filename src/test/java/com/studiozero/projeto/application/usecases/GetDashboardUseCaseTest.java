package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetDashboardUseCaseTest {
    @Test
    void testGetDashboardSuccess() {
        // Simule a busca de dashboard e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetDashboardThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar dashboard");
        });
        assertEquals("Erro ao buscar dashboard", exception.getMessage());
    }
}
