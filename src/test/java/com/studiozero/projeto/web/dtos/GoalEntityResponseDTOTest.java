package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.GoalResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        GoalResponseDTO dto = new GoalResponseDTO();
        dto.setId(1);
        dto.setTitle("Goal");
        assertEquals(1, dto.getId());
        assertEquals("Goal", dto.getTitle());
    }

    @Test
    void testDTOInvalidData() {
        GoalResponseDTO dto = new GoalResponseDTO();
        assertNull(dto.getTitle());
    }
}
