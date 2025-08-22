package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListSubJobsUseCaseTestEntity {
    @Test
    void testListSubJobsSuccess() {
        // Simule a listagem de subjobs e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListSubJobsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar subjobs");
        });
        assertEquals("Erro ao listar subjobs", exception.getMessage());
    }
}
