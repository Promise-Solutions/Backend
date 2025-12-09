package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.ProductResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        Integer id = 123;
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(id);
        dto.setName("Prod");
        dto.setClientValue(12.5);

        assertEquals(id, dto.getId());
        assertEquals("Prod", dto.getName());
        assertEquals(12.5, dto.getClientValue());
    }

    @Test
    void testDTOInvalidData() {
        ProductResponseDTO dto = new ProductResponseDTO();
        assertNull(dto.getId());
        dto.setName(null);
        assertNull(dto.getName());
    }
}
