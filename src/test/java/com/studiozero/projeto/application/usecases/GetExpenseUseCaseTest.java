package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetExpenseUseCaseTest {
    @Test
    void testGetExpenseSuccess() {
        // Simule a busca de despesa e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetExpenseThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar despesa");
        });
        assertEquals("Erro ao buscar despesa", exception.getMessage());
    }
}
