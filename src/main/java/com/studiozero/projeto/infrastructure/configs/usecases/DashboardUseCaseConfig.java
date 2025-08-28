package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.DashboardRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public GetActivesUseCase getActivesUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetActivesUseCase(dashboardRepository);
    }

    @Bean
    public GetBalancesUseCase getBalancesUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetBalancesUseCase(dashboardRepository);
    }

    @Bean
    public GetBarFinancesUseCase getBarFinancesUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetBarFinancesUseCase(dashboardRepository);
    }

    @Bean
    public GetCategoryBalancesUseCase getCategoryBalancesUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetCategoryBalancesUseCase(dashboardRepository);
    }

    @Bean
    public GetClientStatsUseCase getClientStatsUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetClientStatsUseCase(dashboardRepository);
    }

    @Bean
    public GetDashboardDataUseCase getDashboardDataUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetDashboardDataUseCase(dashboardRepository);
    }

    @Bean
    public GetFrequencysUseCase getFrequencysUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetFrequencysUseCase(dashboardRepository);
    }

    @Bean
    public GetRecentTimeUseCase getRecentTimeUseCase(DashboardRepositoryImpl dashboardRepository) {
        return new GetRecentTimeUseCase(dashboardRepository);
    }
}

