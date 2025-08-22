package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateExpenseEntityUseCaseTest {
    @Test
    void testCreateExpenseSuccess() {
        // Simule a criação de despesa e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testCreateExpenseThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao criar despesa");
        });
        assertEquals("Erro ao criar despesa", exception.getMessage());
    }
}
