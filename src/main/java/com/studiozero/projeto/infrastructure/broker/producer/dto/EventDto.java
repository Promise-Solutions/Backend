package com.studiozero.projeto.infrastructure.broker.producer.dto;

import java.util.List;

public record EventDto(
        List<String> employeesEmail,
        List<SubJobEmailEventDto> subJobs,
        List<TaskEmailEventDto> tasks
) {
}
