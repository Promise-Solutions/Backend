package com.studiozero.projeto.web.dtos;

import com.studiozero.projeto.web.dtos.response.ProductResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductEntityResponseDTOTest {
    @Test
    void testDTOCreation() {
        UUID id = UUID.randomUUID();
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(id);
        dto.setName("Prod");
        dto.setPrice(12.5);

        assertEquals(id, dto.getId());
        assertEquals("Prod", dto.getName());
        assertEquals(12.5, dto.getPrice());
    }

    @Test
    void testDTOInvalidData() {
        ProductResponseDTO dto = new ProductResponseDTO();
        assertNull(dto.getId());
        dto.setName(null);
        assertNull(dto.getName());
    }
}
