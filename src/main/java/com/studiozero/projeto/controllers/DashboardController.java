package com.studiozero.projeto.controllers;

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
}
