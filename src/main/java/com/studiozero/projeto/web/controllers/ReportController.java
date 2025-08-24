package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.infrastructure.services.GenerateExcelReport;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/report")
@Tag(name = "Report", description = "Endpoints for Report Management")
public class ReportController {
    private final GenerateExcelReport generateExcelReport;

    public ReportController(GenerateExcelReport generateExcelReport) {
        this.generateExcelReport = generateExcelReport;
    }

    @GetMapping("/generate-excel")
    public ResponseEntity<Resource> generateFullExcelReport() {
        File file = generateExcelReport.execute();

        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            String fileName = file.getName();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .body(resource);

        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
