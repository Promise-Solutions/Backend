package com.studiozero.projeto.shared.application.usecases.dashboard;

import com.studiozero.projeto.shared.repositories.DashboardRepository;
import java.util.Map;

public class GetClientStatsUseCase {
    private final DashboardRepository dashboardRepository;

    public GetClientStatsUseCase(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Map<String, Double> execute(Object clientId) {
        return (Map<String, Double>) dashboardRepository.getClientStats(clientId);
    }
}
