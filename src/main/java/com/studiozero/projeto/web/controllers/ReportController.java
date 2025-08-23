package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.infrastructure.services.GenerateExcelReport;
import com.studiozero.projeto.domain.repositories.EmailRepository;
import com.studiozero.projeto.web.dtos.request.SendReportEmailRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Tag(name = "Report", description = "Endpoints for Report Management")
public class ReportController {
    private final GenerateExcelReport generateExcelReport;
    private final EmailRepository emailRepository;

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

    @PostMapping("/send-excel")
    public ResponseEntity<Void> sendExcelReportByEmail(@RequestBody SendReportEmailRequest request) {
        File file = generateExcelReport.execute();
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            String fileName = file.getName();
            emailRepository.sendEmailWithAttachment(
                request.getDestinatarios(),
                "Relatório StudioZero",
                "Segue em anexo o relatório gerado.",
                fileName,
                fileBytes
            );
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
