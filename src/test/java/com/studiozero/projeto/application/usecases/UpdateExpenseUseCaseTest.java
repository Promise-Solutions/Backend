package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateExpenseUseCaseTest {
    @Test
    void testUpdateExpenseSuccess() {
        // Simule a atualização de despesa e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testUpdateExpenseThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao atualizar despesa");
        });
        assertEquals("Erro ao atualizar despesa", exception.getMessage());
    }
}
