package com.studiozero.projeto.application.usecases.appointment;

import com.studiozero.projeto.domain.repositories.AppointmentRepository;
import com.studiozero.projeto.web.handlers.BadRequestException;

public class GetMonthAppointmentsUseCase {
    private final AppointmentRepository repository;

    public GetMonthAppointmentsUseCase(AppointmentRepository repository) {
        this.repository = repository;
    }

    public AppointmentsFoundDto execute(int year, int month) {
        if(year <= 0) {
            throw new BadRequestException("Year has a 0 or negative value");
        }

        if(month > 12 || month < 1) {
            throw new BadRequestException("Month has a impossible value, it must be between 1 and 12");
        }

        return repository.findAppointmentsOfTheMonth(year, month);
    }
}
