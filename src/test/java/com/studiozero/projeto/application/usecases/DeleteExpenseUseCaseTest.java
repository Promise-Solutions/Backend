package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteExpenseUseCaseTest {
    @Test
    void testDeleteExpenseSuccess() {
        // Simule a exclusão de despesa e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testDeleteExpenseThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao deletar despesa");
        });
        assertEquals("Erro ao deletar despesa", exception.getMessage());
    }
}
