package com.studiozero.projeto.application.usecases.dashboard;

import java.time.LocalDateTime;
import com.studiozero.projeto.domain.repositories.DashboardRepository;

public class GetRecentTimeUseCase {
    private final DashboardRepository dashboardRepository;

    public GetRecentTimeUseCase(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public LocalDateTime execute() {
        return dashboardRepository.getRecentTime();
    }
}
