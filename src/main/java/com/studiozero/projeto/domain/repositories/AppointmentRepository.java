package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.application.usecases.appointment.AppointmentsFoundDto;

public interface AppointmentRepository {
    AppointmentsFoundDto findAppointmentsOfTheMonth(int year, int month);
}
