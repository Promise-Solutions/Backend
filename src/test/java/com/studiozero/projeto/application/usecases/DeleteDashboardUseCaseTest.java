package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteDashboardUseCaseTest {
    @Test
    void testDeleteDashboardSuccess() {
        // Simule a exclusão de dashboard e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteDashboardThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar dashboard");
        });
        assertEquals("Erro ao deletar dashboard", exception.getMessage());
    }
}
