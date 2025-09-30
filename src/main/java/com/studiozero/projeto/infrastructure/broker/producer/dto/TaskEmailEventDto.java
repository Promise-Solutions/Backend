package com.studiozero.projeto.infrastructure.broker.producer.dto;

import java.time.LocalDate;

public record TaskEmailEventDto(
        String title,
        LocalDate limitDate
) {
}
