package com.studiozero.projeto.infrastructure.broker.producer.dto;

public record SubJobEmailEventDto(
        String clientName,
        String title
) {
}
