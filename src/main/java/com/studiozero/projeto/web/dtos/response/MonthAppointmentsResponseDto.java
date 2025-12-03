package com.studiozero.projeto.web.dtos.response;

import java.util.List;

public class MonthAppointmentsResponseDto {
    private final List<SubJobResponseDTO> subJobs;
    private final List<TaskResponseDTO> tasks;

    public MonthAppointmentsResponseDto(List<SubJobResponseDTO> subJobs, List<TaskResponseDTO> tasks) {
        this.subJobs = subJobs;
        this.tasks = tasks;
    }

    public List<SubJobResponseDTO> getSubJobs() {
        return subJobs;
    }

    public List<TaskResponseDTO> getTasks() {
        return tasks;
    }
}
