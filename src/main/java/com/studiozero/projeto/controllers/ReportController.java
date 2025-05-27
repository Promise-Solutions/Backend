package com.studiozero.projeto.controllers;

import com.studiozero.projeto.services.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Tag(name = "Report", description = "Endpoints for Report Management")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/generate-csv")
    public ResponseEntity<Resource> downloadRelatorioMax() throws IOException {
        File csv = reportService.gerarRelatorioCSV();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(csv));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + csv.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .contentLength(csv.length())
                .body(resource);
    }

    @GetMapping("/generate-excel")
    public ResponseEntity<Resource> gerarRelatorioCompletoExcel() {
        File arquivo = reportService.gerarRelatorioExcel();

        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(arquivo));

            String nomeArquivo = arquivo.getName();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivo + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(arquivo.length())
                    .body(resource);

        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
