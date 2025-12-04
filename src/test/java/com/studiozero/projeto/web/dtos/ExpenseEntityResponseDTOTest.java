package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.ExpenseResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setId(1);
        dto.setDescription("desc");
        dto.setAmountSpend(10.5);

        assertEquals(1, dto.getId());
        assertEquals("desc", dto.getDescription());
        assertEquals(10.5, dto.getAmountSpend());
    }

    @Test
    void testDTOInvalidData() {
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        assertNull(dto.getDescription());
    }
}
