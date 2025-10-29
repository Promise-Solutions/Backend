package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.dashboard.GetClientStatsUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetFrequencysUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetActivesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetCategoryBalancesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetBarFinancesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetBalancesUseCase;
import com.studiozero.projeto.application.usecases.dashboard.GetRecentTimeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Endpoints for Dashboard Management")
public class DashboardController {

    private final GetClientStatsUseCase getClientStatsUseCase;
    private final GetFrequencysUseCase getFrequencysUseCase;
    private final GetActivesUseCase getActivesUseCase;
    private final GetCategoryBalancesUseCase getCategoryBalancesUseCase;
    private final GetBarFinancesUseCase getBarFinancesUseCase;
    private final GetBalancesUseCase getBalancesUseCase;
    private final GetRecentTimeUseCase getRecentTimeUseCase;

    public DashboardController(GetClientStatsUseCase getClientStatsUseCase, GetFrequencysUseCase getFrequencysUseCase, GetActivesUseCase getActivesUseCase, GetCategoryBalancesUseCase getCategoryBalancesUseCase, GetBarFinancesUseCase getBarFinancesUseCase, GetBalancesUseCase getBalancesUseCase, GetRecentTimeUseCase getRecentTimeUseCase) {
        this.getClientStatsUseCase = getClientStatsUseCase;
        this.getFrequencysUseCase = getFrequencysUseCase;
        this.getActivesUseCase = getActivesUseCase;
        this.getCategoryBalancesUseCase = getCategoryBalancesUseCase;
        this.getBarFinancesUseCase = getBarFinancesUseCase;
        this.getBalancesUseCase = getBalancesUseCase;
        this.getRecentTimeUseCase = getRecentTimeUseCase;
    }

    @Operation(summary = "Get client-specific statistics", description = "Returns frequency and financial return of subjobs and jobs for a specific client, including total closed commands.")
    @GetMapping("/client-stats/{clientId}")
    public ResponseEntity<Map<String, Double>> getClientStats(
            @PathVariable UUID clientId) {
        return ResponseEntity.ok(getClientStatsUseCase.execute(clientId));
    }

    @Operation(summary = "Get frequency statistics", description = "Returns frequency data by client type and service category.")
    @GetMapping("/frequencys")
    public ResponseEntity<Map<String, Double>> getFrequencys() {
        return ResponseEntity.ok(getFrequencysUseCase.execute());
    }

    @Operation(summary = "Get actives statistics", description = "Returns actives data by client type")
    @GetMapping("/actives")
    public ResponseEntity<Map<String, Double>> getActives() {
        return ResponseEntity.ok(getActivesUseCase.execute());
    }

    @Operation(summary = "Get job balances per category statistics", description = "Returns balances data by job type")
    @GetMapping("/category-balances")
    public ResponseEntity<Map<String, Double>> getCategoryBalances() {
        return ResponseEntity.ok(getCategoryBalancesUseCase.execute());
    }

    @Operation(summary = "Get bar balances statistics", description = "Returns bar balances")
    @GetMapping("/bar-balances")
    public ResponseEntity<Map<String, Double>> getBarBalances() {
        return ResponseEntity.ok(getBarFinancesUseCase.execute());
    }

    @Operation(summary = "Get balances statistics", description = "Returns balances")
    @GetMapping("/balances")
    public ResponseEntity<Map<String, Double>> getBalances() {
        return ResponseEntity.ok(getBalancesUseCase.execute());
    }

    @Operation(summary = "Get balances statistics", description = "Returns balances data by job type")
    @GetMapping("/recent-time")
    public ResponseEntity<LocalDateTime> getRecentTime() {
        return ResponseEntity.ok(getRecentTimeUseCase.execute());
    }
}
