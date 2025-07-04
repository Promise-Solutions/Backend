package com.studiozero.projeto.controllers;

import com.studiozero.projeto.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints for Dashboard Management")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Get client-specific statistics",
            description = "Returns frequency and financial return of subjobs and jobs for a specific client, including total closed commands."
    )
    @GetMapping("/client-stats/{clientId}")
    public ResponseEntity<Map<String, Double>> getClientStats(
            @PathVariable UUID clientId
    ) {
        return ResponseEntity.ok(dashboardService.getClientStats(clientId));
    }

    @Operation(
            summary = "Get frequency statistics",
            description = "Returns frequency data by client type and service category."
    )
    @GetMapping("/frequencys")
    public ResponseEntity<Map<String, Double>> getFrequencys() {
        return ResponseEntity.ok(dashboardService.getFrequencys());
    }

    @Operation(
            summary = "Get actives statistics",
            description = "Returns actives data by client type"
    )
    @GetMapping("/actives")
    public ResponseEntity<Map<String, Double>> getActives() {
        return ResponseEntity.ok(dashboardService.getActives());
    }

    @Operation(
            summary = "Get job balances per category statistics",
            description = "Returns balances data by job type"
    )
    @GetMapping("/category-balances")
    public ResponseEntity<Map<String, Double>> getCategoryBalances() {
        return ResponseEntity.ok(dashboardService.getCategoryBalances());
    }
    @Operation(
            summary = "Get bar balances statistics",
            description = "Returns bar balances"
    )
    @GetMapping("/bar-balances")
    public ResponseEntity<Map<String, Double>> getBarBalances() {
        return ResponseEntity.ok(dashboardService.getBarFinances());
    }

    @Operation(
            summary = "Get balances statistics",
            description = "Returns balances"
    )
    @GetMapping("/balances")
    public ResponseEntity<Map<String, Double>> getBalances() {
        return ResponseEntity.ok(dashboardService.getBalances());
    }

    @Operation(
            summary = "Get balances statistics",
            description = "Returns balances data by job type"
    )
    @GetMapping("/recent-time")
    public ResponseEntity<LocalDateTime> getRecentTime() {
        return ResponseEntity.ok(dashboardService.getRecentTime());
    }
}
