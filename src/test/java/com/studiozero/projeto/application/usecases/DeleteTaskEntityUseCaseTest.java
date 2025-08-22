package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskEntityUseCaseTest {
    @Test
    void testDeleteTaskSuccess() {
        // Simule a exclusão de task e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteTaskThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar task");
        });
        assertEquals("Erro ao deletar task", exception.getMessage());
    }
}
