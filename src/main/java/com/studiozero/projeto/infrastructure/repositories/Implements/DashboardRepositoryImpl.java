package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.repositories.DashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@Repository
@AllArgsConstructor
public class DashboardRepositoryImpl implements DashboardRepository {
    @Override
    public Object getDashboardData() {
        // Implementação mock
        return null;
    }

    @Override
    public Map<String, Double> getClientStats(Object clientId) {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getFrequencys() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getActives() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getBarFinances() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getCategoryBalances() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getBalances() {
        return new HashMap<>();
    }

    @Override
    public LocalDateTime getRecentTime() {
        return null;
    }
}
