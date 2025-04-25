package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.response.DashboardResponseDto;
import com.studiozero.projeto.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints for Dashboard Management")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Get global dashboard statistics",
            description = "Returns general dashboard statistics including financial return, output and active client frequency."
    )
    @GetMapping("/all")
    public ResponseEntity<DashboardResponseDto> getAllStats() {
        DashboardResponseDto response = dashboardService.allStats();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get dashboard statistics for a specific client",
            description = "Returns dashboard statistics filtered by a specific client, including financial return and activity frequency."
    )
    @GetMapping("/client/{id}")
    public ResponseEntity<DashboardResponseDto> getClientStats(@PathVariable UUID id) {
        DashboardResponseDto response = dashboardService.clientStats(id);
        return ResponseEntity.ok(response);
    }
}
