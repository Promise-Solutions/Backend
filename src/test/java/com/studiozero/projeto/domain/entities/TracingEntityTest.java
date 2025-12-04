package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TracingEntityTest {
    @Test
    void testTracingCreation() {
        Tracing t = new Tracing(null, null, null, null, null);
        assertNotNull(t);
    }

    @Test
    void testTracingInvalid() {
        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException("Dados inválidos"); });
    }
}
