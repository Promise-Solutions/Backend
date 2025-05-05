package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.response.DashboardResponseDto;
import com.studiozero.projeto.enums.JobCategory;
import com.studiozero.projeto.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints for Dashboard Management")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Get general statistics for subjobs and jobs", description = "Returns frequency and financial return of subjobs and jobs, separated by categories.")
    @GetMapping("/general-stats")
    public ResponseEntity<Map<JobCategory, Map<String, Double>>> getGeneralStats() {
        return ResponseEntity.ok(dashboardService.getGeneralStats());
    }

    @Operation(summary = "Get client-specific statistics", description = "Returns frequency and financial return of subjobs and jobs for a specific client, including total closed commands.")
    @GetMapping("/client-stats/{clientId}")
    public ResponseEntity<Map<String, Double>> getClientStats(@PathVariable UUID clientId) {
        return ResponseEntity.ok(dashboardService.getClientStats(clientId));
    }

    @Operation(summary = "Get bar finances", description = "Returns total value of open and closed commands, and total cost of purchased products.")
    @GetMapping("/bar-finances")
    public ResponseEntity<Map<String, Double>> getBarFinances() {
        return ResponseEntity.ok(dashboardService.getBarFinances());
    }
}
