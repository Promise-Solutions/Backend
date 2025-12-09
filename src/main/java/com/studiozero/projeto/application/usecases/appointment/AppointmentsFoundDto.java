package com.studiozero.projeto.application.usecases.appointment;

import com.studiozero.projeto.domain.entities.SubJob;
import com.studiozero.projeto.domain.entities.Task;

import java.util.List;

public record AppointmentsFoundDto(
        List<SubJob> subJobs,
        List<Task> tasks
) {
}
