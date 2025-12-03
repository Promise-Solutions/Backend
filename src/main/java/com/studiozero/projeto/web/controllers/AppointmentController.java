package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.appointment.AppointmentsFoundDto;
import com.studiozero.projeto.application.usecases.appointment.GetMonthAppointmentsUseCase;
import com.studiozero.projeto.web.dtos.response.MonthAppointmentsResponseDto;
import com.studiozero.projeto.web.mappers.SubJobMapper;
import com.studiozero.projeto.web.mappers.TaskMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final GetMonthAppointmentsUseCase getMonthAppointmentsUseCase;

    public AppointmentController(GetMonthAppointmentsUseCase getMonthAppointmentsUseCase) {
        this.getMonthAppointmentsUseCase = getMonthAppointmentsUseCase;
    }

    @GetMapping
    public ResponseEntity<MonthAppointmentsResponseDto> listAppointmentsOfTheWeek(
            @RequestParam int year,
            @RequestParam int month
    ) {
        AppointmentsFoundDto response = getMonthAppointmentsUseCase.execute(year, month);
        return ResponseEntity.status(200).body(
                new MonthAppointmentsResponseDto(
                    SubJobMapper.toDTOList(response.subJobs()),
                    TaskMapper.toDTOList(response.tasks())
                )
        );
    }
}
