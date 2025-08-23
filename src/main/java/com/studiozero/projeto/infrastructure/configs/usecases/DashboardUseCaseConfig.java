package com.studiozero.projeto.infrastructure.configs.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.DashboardRepository;
import com.studiozero.projeto.application.usecases.dashboard.GetActivesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetBalancesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetBarFinancesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetCategoryBalancesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetClientStatsUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetDashboardDataUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetFrequencysUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetRecentTimeUseCase;

@Configuration
public class DashboardUseCaseConfig {
    @Bean
    public GetActivesUseCase getActivesUseCase(DashboardRepository dashboardRepository) {
        return new GetActivesUseCase(dashboardRepository);
    }

    @Bean
    public GetBalancesUseCase getBalancesUseCase(DashboardRepository dashboardRepository) {
        return new GetBalancesUseCase(dashboardRepository);
    }

    @Bean
    public GetBarFinancesUseCase getBarFinancesUseCase(DashboardRepository dashboardRepository) {
        return new GetBarFinancesUseCase(dashboardRepository);
    }

    @Bean
    public GetCategoryBalancesUseCase getCategoryBalancesUseCase(DashboardRepository dashboardRepository) {
        return new GetCategoryBalancesUseCase(dashboardRepository);
    }

    @Bean
    public GetClientStatsUseCase getClientStatsUseCase(DashboardRepository dashboardRepository) {
        return new GetClientStatsUseCase(dashboardRepository);
    }

    @Bean
    public GetDashboardDataUseCase getDashboardDataUseCase(DashboardRepository dashboardRepository) {
        return new GetDashboardDataUseCase(dashboardRepository);
    }

    @Bean
    public GetFrequencysUseCase getFrequencysUseCase(DashboardRepository dashboardRepository) {
        return new GetFrequencysUseCase(dashboardRepository);
    }

    @Bean
    public GetRecentTimeUseCase getRecentTimeUseCase(DashboardRepository dashboardRepository) {
        return new GetRecentTimeUseCase(dashboardRepository);
    }
}

