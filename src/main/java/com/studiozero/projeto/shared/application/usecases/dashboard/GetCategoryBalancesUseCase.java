package com.studiozero.projeto.shared.application.usecases.dashboard;

import com.studiozero.projeto.shared.repositories.DashboardRepository;
import java.util.Map;

public class GetCategoryBalancesUseCase {
    private final DashboardRepository dashboardRepository;

    public GetCategoryBalancesUseCase(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Map<String, Double> execute() {
        return (Map<String, Double>) dashboardRepository.getCategoryBalances();
    }
}
