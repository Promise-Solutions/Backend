package com.studiozero.projeto.controllers;

import com.studiozero.projeto.services.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Tag(name = "Report", description = "Endpoints for Report Management")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadRelatorio() throws IOException {
        File csv = reportService.gerarRelatorioCSV();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(csv));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + csv.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .contentLength(csv.length())
                .body(resource);
    }

}
