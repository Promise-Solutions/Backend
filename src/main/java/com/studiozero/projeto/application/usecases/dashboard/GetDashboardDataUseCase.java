package com.studiozero.projeto.application.usecases.dashboard;

import com.studiozero.projeto.domain.repositories.DashboardRepository;

public class GetDashboardDataUseCase {
    private final DashboardRepository dashboardRepository;

    public GetDashboardDataUseCase(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Object execute() {
        // Retorna dados do dashboard, tipo Object para ser adaptável
        return dashboardRepository.getDashboardData();
    }
}
