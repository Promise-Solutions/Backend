package com.studiozero.projeto.shared.application.usecases.dashboard;

import com.studiozero.projeto.shared.repositories.DashboardRepository;

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
