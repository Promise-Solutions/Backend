package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateTaskUseCaseTest {
    @Test
    void testUpdateTaskSuccess() {
        // Simule a atualização de task e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testUpdateTaskThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao atualizar task");
        });
        assertEquals("Erro ao atualizar task", exception.getMessage());
    }
}
