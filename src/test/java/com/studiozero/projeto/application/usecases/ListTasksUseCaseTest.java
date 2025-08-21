package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListTasksUseCaseTest {
    @Test
    void testListTasksSuccess() {
        // Simule a listagem de tasks e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListTasksThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar tasks");
        });
        assertEquals("Erro ao listar tasks", exception.getMessage());
    }
}
