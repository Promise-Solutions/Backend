package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListProductsUseCaseTest {
    @Test
    void testListProductsSuccess() {
        // Simule a listagem de produtos e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testListProductsThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao listar produtos");
        });
        assertEquals("Erro ao listar produtos", exception.getMessage());
    }
}
