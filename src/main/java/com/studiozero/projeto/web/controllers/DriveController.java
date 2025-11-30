package com.studiozero.projeto.web.controllers;

import com.google.api.services.drive.model.File;
import com.studiozero.projeto.infrastructure.repositories.services.DriveServiceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drive")
@Tag(name = "Google Drive API", description = "Endpoints for Google Drive")
public class DriveController {

    private final DriveServiceRepository driveRepository;

    public DriveController(DriveServiceRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    @Operation(summary = "Send a file to drive", description = "This method is responsible for sending a file to drive.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart("file") MultipartFile file) {
        try {
            String fileId = driveRepository.uploadFile(file);
            return ResponseEntity.ok("File sent successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload error: " + e.getMessage());
        }
    }

    @Operation(summary = "List files from drive", description = "This method is responsible for listing files from drive.")
    @GetMapping
    public ResponseEntity<List<FileInfoDto>> listFiles() {
        try {
            List<File> files = driveRepository.listFiles();
            List<FileInfoDto> fileDtos = files.stream()
                .map(f -> new FileInfoDto(f.getId(), f.getName()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(fileDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Download file from drive", description = "This method is responsible for downloading a file from drive by its ID.")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) {
        try {
            byte[] fileBytes = driveRepository.downloadFile(fileId);
            return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=downloaded_file")
                .body(fileBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete file from drive", description = "This method is responsible for deleting a file from drive by its ID.")
    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
        try {
            driveRepository.deleteFile(fileId);
            return ResponseEntity.ok("File deleted successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Delete error: " + e.getMessage());
        }
    }

    public record FileInfoDto(String id, String name) {}
}