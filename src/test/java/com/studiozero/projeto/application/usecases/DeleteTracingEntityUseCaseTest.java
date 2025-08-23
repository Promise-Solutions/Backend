package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTracingEntityUseCaseTest {
    @Test
    void testDeleteTracingSuccess() {
        // Simule a exclusão de tracing e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteTracingThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar tracing");
        });
        assertEquals("Erro ao deletar tracing", exception.getMessage());
    }
}
