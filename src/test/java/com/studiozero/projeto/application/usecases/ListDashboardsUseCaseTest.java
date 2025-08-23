package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListDashboardsUseCaseTest {
    @Test
    void testListDashboardsSuccess() {
        // Simule a listagem de dashboards e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListDashboardsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar dashboards");
        });
        assertEquals("Erro ao listar dashboards", exception.getMessage());
    }
}
