package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.GoalResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        GoalResponseDTO dto = new GoalResponseDTO();
        dto.setId(1);
        dto.setValue(100.0);
        assertEquals(1, dto.getId());
        assertEquals(100.00, dto.getValue());
    }

    @Test
    void testDTOInvalidData() {
        GoalResponseDTO dto = new GoalResponseDTO();
        assertNull(dto.getValue());
    }
}
