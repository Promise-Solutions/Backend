package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.appointment.GetMonthAppointmentsUseCase;
import com.studiozero.projeto.infrastructure.repositories.Implements.AppointmentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentUseCaseConfig {
    @Bean
    public GetMonthAppointmentsUseCase calendarRepositoryBean(AppointmentRepositoryImpl calendarRepository) {
        return new GetMonthAppointmentsUseCase(calendarRepository);
    }
}
