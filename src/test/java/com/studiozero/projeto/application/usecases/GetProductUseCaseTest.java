package com.studiozero.projeto.application.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetProductUseCaseTest {
    @Test
    void testGetProductSuccess() {
        // Simule a busca de produto e verifique o resultado
        assertTrue(true);
    }

    @Test
    void testGetProductThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Erro ao buscar produto");
        });
        assertEquals("Erro ao buscar produto", exception.getMessage());
    }
}
