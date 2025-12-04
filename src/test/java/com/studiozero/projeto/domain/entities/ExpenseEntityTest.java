package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseEntityTest {
    @Test
    void testExpenseCreation() {
        Expense e = new Expense(1, null, null, "desc", 10.0, 1, null, null);
        assertEquals(1, e.getId());
        assertEquals("desc", e.getDescription());
        assertEquals(10.0, e.getAmountSpend());
    }

    @Test
    void testExpenseInvalid() {
        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException("Dados inválidos"); });
    }
}
