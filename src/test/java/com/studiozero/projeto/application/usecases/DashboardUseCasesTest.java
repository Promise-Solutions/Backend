package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.dashboard.*;
import com.studiozero.projeto.domain.repositories.DashboardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DashboardUseCasesTest {

    @Mock
    private DashboardRepository dashboardRepository;

    @InjectMocks
    private GetActivesUseCase getActivesUseCase;

    @InjectMocks
    private GetBalancesUseCase getBalancesUseCase;

    @InjectMocks
    private GetBarFinancesUseCase getBarFinancesUseCase;

    @InjectMocks
    private GetCategoryBalancesUseCase getCategoryBalancesUseCase;

    @InjectMocks
    private GetClientStatsUseCase getClientStatsUseCase;

    @InjectMocks
    private GetDashboardDataUseCase getDashboardDataUseCase;

    @InjectMocks
    private GetFrequencysUseCase getFrequencysUseCase;

    @InjectMocks
    private GetRecentTimeUseCase getRecentTimeUseCase;


    @Test
    @DisplayName("Should return actives map")
    void shouldReturnActives() {
        Map<String, Double> expected = Map.of("total", 1500.0);
        when(dashboardRepository.getActives()).thenReturn(expected);

        Map<String, Double> result = getActivesUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getActives();
    }

    @Test
    @DisplayName("Should return balances map")
    void shouldReturnBalances() {
        Map<String, Double> expected = Map.of("balance", 3000.0);
        when(dashboardRepository.getBalances()).thenReturn(expected);

        Map<String, Double> result = getBalancesUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getBalances();
    }

    @Test
    @DisplayName("Should return bar finances map")
    void shouldReturnBarFinances() {
        Map<String, Double> expected = Map.of("bar", 900.0);
        when(dashboardRepository.getBarFinances()).thenReturn(expected);

        Map<String, Double> result = getBarFinancesUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getBarFinances();
    }

    @Test
    @DisplayName("Should return category balances map")
    void shouldReturnCategoryBalances() {
        Map<String, Double> expected = Map.of("food", 450.0);
        when(dashboardRepository.getCategoryBalances()).thenReturn(expected);

        Map<String, Double> result = getCategoryBalancesUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getCategoryBalances();
    }

    @Test
    @DisplayName("Should return client stats map")
    void shouldReturnClientStats() {
        Object clientId = 123;
        Map<String, Double> expected = Map.of("visits", 12.0);
        when(dashboardRepository.getClientStats(clientId)).thenReturn(expected);

        Map<String, Double> result = getClientStatsUseCase.execute(clientId);

        assertEquals(expected, result);
        verify(dashboardRepository).getClientStats(clientId);
    }

    @Test
    @DisplayName("Should return dashboard data")
    void shouldReturnDashboardData() {
        Object expected = new Object();
        when(dashboardRepository.getDashboardData()).thenReturn(expected);

        Object result = getDashboardDataUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getDashboardData();
    }

    @Test
    @DisplayName("Should return frequencys map")
    void shouldReturnFrequencys() {
        Map<String, Double> expected = Map.of("mon", 20.0);
        when(dashboardRepository.getFrequencys()).thenReturn(expected);

        Map<String, Double> result = getFrequencysUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getFrequencys();
    }

    @Test
    @DisplayName("Should return recent time")
    void shouldReturnRecentTime() {
        LocalDateTime expected = LocalDateTime.now();
        when(dashboardRepository.getRecentTime()).thenReturn(expected);

        LocalDateTime result = getRecentTimeUseCase.execute();

        assertEquals(expected, result);
        verify(dashboardRepository).getRecentTime();
    }
}
