package com.studiozero.projeto.domain.repositories;

import java.time.LocalDateTime;
import java.util.Map;

public interface DashboardRepository {
    Object getDashboardData();

    Map<String, Double> getClientStats(Object clientId);

    Map<String, Double> getFrequencys();

    Map<String, Double> getActives();

    Map<String, Double> getBarFinances();

    Map<String, Double> getCategoryBalances();

    Map<String, Double> getBalances();

    LocalDateTime getRecentTime();
}
