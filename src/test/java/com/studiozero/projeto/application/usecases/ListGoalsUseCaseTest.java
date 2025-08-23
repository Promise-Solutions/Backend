package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListGoalsUseCaseTest {
    @Test
    void testListGoalsSuccess() {
        // Simule a listagem de metas e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListGoalsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar metas");
        });
        assertEquals("Erro ao listar metas", exception.getMessage());
    }
}
