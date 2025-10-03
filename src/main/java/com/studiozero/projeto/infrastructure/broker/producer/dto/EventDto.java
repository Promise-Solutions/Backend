package com.studiozero.projeto.infrastructure.broker.producer.dto;

import java.util.List;

public record EventDto(
        List<String> to,
        List<SubJobEmailEventDto> subJobs,
        List<TaskEmailEventDto> tasks
) {
}
