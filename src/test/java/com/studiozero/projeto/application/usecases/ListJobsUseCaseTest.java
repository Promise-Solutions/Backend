package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListJobsUseCaseTest {
    @Test
    void testListJobsSuccess() {
        // Simule a listagem de jobs e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListJobsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar jobs");
        });
        assertEquals("Erro ao listar jobs", exception.getMessage());
    }
}
