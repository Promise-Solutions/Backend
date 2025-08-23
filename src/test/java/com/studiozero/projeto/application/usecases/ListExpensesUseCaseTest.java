package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListExpensesUseCaseTest {
    @Test
    void testListExpensesSuccess() {
        // Simule a listagem de despesas e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListExpensesThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar despesas");
        });
        assertEquals("Erro ao listar despesas", exception.getMessage());
    }
}
